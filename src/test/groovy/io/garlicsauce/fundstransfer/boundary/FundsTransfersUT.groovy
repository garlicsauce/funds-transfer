package io.garlicsauce.fundstransfer.boundary

import io.garlicsauce.fundstransfer.api.FundsTransferRequest
import io.garlicsauce.fundstransfer.domain.Account
import io.garlicsauce.fundstransfer.domain.AccountNotFoundException
import io.garlicsauce.fundstransfer.domain.AccountRepository
import io.garlicsauce.fundstransfer.domain.MoneyExchanger
import io.garlicsauce.fundstransfer.shared.AmountAndCurrency
import io.garlicsauce.fundstransfer.shared.Currency
import spock.lang.Specification
import spock.lang.Subject

class FundsTransfersUT extends Specification {

    def accountRepositoryMock = Mock(AccountRepository)
    def moneyExchangerMock = Mock(MoneyExchanger)

    @Subject
    def fundsTransfers = new FundsTransfers(accountRepositoryMock, moneyExchangerMock)

    def "should perform transfer of funds"() {
        given:
            def senderId = UUID.randomUUID()
            def recipientId = UUID.randomUUID()
            def fundsTransferRequest = sampleFundsTransferRequest(senderId, recipientId)

        and:
            def senderAccountMock = Mock(Account)
            def recipientAccountMock = Mock(Account)

        when:
            fundsTransfers.transferFunds(fundsTransferRequest)

        then:
            1 * accountRepositoryMock.findByOwnerId(senderId) >> Optional.of(senderAccountMock)
            1 * accountRepositoryMock.findByOwnerId(recipientId) >> Optional.of(recipientAccountMock)

        and:
            1 * senderAccountMock.subtractMoney(fundsTransferRequest.balance, moneyExchangerMock) >> senderAccountMock
            1 * recipientAccountMock.addMoney(fundsTransferRequest.balance, moneyExchangerMock) >> recipientAccountMock

        and:
            1 * accountRepositoryMock.save(senderAccountMock)
            1 * accountRepositoryMock.save(recipientAccountMock)
    }

    def "should throw AccountNotFoundException when trying to transfer funds from non-existing account"() {
        given:
            def senderId = UUID.randomUUID()
            def recipientId = UUID.randomUUID()
            def fundsTransferRequest = sampleFundsTransferRequest(senderId, recipientId)

        and:
            accountRepositoryMock.findByOwnerId(senderId) >> Optional.empty()

        when:
            fundsTransfers.transferFunds(fundsTransferRequest)

        then:
            thrown(AccountNotFoundException)
    }

    def "should throw AccountNotFoundException when trying to transfer funds to non-existing account"() {
        given:
            def senderId = UUID.randomUUID()
            def recipientId = UUID.randomUUID()
            def fundsTransferRequest = sampleFundsTransferRequest(senderId, recipientId)

        and:
            accountRepositoryMock.findByOwnerId(senderId) >> Optional.of(Mock(Account))
            accountRepositoryMock.findByOwnerId(recipientId) >> Optional.empty()

        when:
            fundsTransfers.transferFunds(fundsTransferRequest)

        then:
            thrown(AccountNotFoundException)
    }

    def sampleFundsTransferRequest(UUID senderId, UUID recipientId) {
        new FundsTransferRequest(senderId, recipientId, new AmountAndCurrency(BigDecimal.ONE, Currency.EUR))
    }
}
