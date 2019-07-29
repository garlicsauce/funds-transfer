package spec

import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.garlicsauce.fundstransfer.FundsTransferApplication
import io.garlicsauce.fundstransfer.domain.AccountRepository
import io.garlicsauce.fundstransfer.shared.Currency
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = [FundsTransferApplication], webEnvironment = RANDOM_PORT)
class FundsTransferFT extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    AccountRepository accountRepository

    @Rule
    WireMockRule wireMockRule = WireMockTestConfig.defaultWireMockRule()

    def "should transfer money in the same currency"() {
        given:
            def senderId = UUID.fromString('e719da9b-dbf4-41bd-a802-cf8d5fb9e6e4')
            assert accountRepository.findByOwnerId(senderId).get().balance.amount == BigDecimal.valueOf(1000)
            assert accountRepository.findByOwnerId(senderId).get().balance.currency == Currency.EUR

        and:
            def recipientId = UUID.fromString('d8d4f285-d214-4ce4-81cc-1faab82f1bb6')
            assert accountRepository.findByOwnerId(recipientId).get().balance.amount == BigDecimal.valueOf(200)
            assert accountRepository.findByOwnerId(recipientId).get().balance.currency == Currency.EUR

        when:
            def call = mockMvc.perform(post('/fundsTransfers')
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("""
                        {
                            "senderId": "$senderId",
                            "recipientId": "$recipientId",
                            "balance": {
                                "amount": "100",
                                "currency": "EUR"
                            }
                        }
                    """))

        then:
            call.andExpect(status().isCreated())

        and:
            accountRepository.findByOwnerId(senderId).get().balance.amount == BigDecimal.valueOf(900)
            accountRepository.findByOwnerId(senderId).get().balance.currency == Currency.EUR

        and:
            accountRepository.findByOwnerId(recipientId).get().balance.amount == BigDecimal.valueOf(300)
            accountRepository.findByOwnerId(recipientId).get().balance.currency == Currency.EUR
    }

    def "should transfer money in different currencies"() {
        given:
            def senderId = UUID.fromString('0c3b8117-55fb-4e15-8702-c10a691edfbf')
            assert accountRepository.findByOwnerId(senderId).get().balance.amount == BigDecimal.valueOf(2000)
            assert accountRepository.findByOwnerId(senderId).get().balance.currency == Currency.PLN

        and:
            def recipientId = UUID.fromString('eab3d3f0-b1c9-4e42-8ce4-17f43b476e8e')
            assert accountRepository.findByOwnerId(recipientId).get().balance.amount == BigDecimal.valueOf(5000)
            assert accountRepository.findByOwnerId(recipientId).get().balance.currency == Currency.CHF

        when:
            def call = mockMvc.perform(post('/fundsTransfers')
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("""
                        {
                            "senderId": "$senderId",
                            "recipientId": "$recipientId",
                            "balance": {
                                "amount": "1000",
                                "currency": "PLN"
                            }
                        }
                    """))

        then:
            call.andExpect(status().isCreated())

        and:
            accountRepository.findByOwnerId(senderId).get().balance.amount == BigDecimal.valueOf(1000)
            accountRepository.findByOwnerId(senderId).get().balance.currency == Currency.PLN

        and:
            accountRepository.findByOwnerId(recipientId).get().balance.amount == BigDecimal.valueOf(5259.03)
            accountRepository.findByOwnerId(recipientId).get().balance.currency == Currency.CHF
    }

    def "should properly respond even if exchange rates api is down"() {
        when:
            def call = mockMvc.perform(post('/fundsTransfers')
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("""
                        {
                            "senderId": "eab3d3f0-b1c9-4e42-8ce4-17f43b476e8e",
                            "recipientId": "d8d4f285-d214-4ce4-81cc-1faab82f1bb6",
                            "balance": {
                                "amount": "1000",
                                "currency": "CHF"
                            }
                        }
                    """))

        then:
            call.andExpect(status().isInternalServerError())
    }
}
