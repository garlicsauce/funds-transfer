package io.garlicsauce.fundstransfer.api

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FundsTransfersControllerUT extends Specification {

    @Subject
    def controller = new FundsTransfersController()

    def mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def "POST /fundsTransfers with proper body should result in 201 CREATED"() {
        given:
            def payload = """
                {
                    "senderId": "${UUID.randomUUID()}",
                    "recipientId": "${UUID.randomUUID()}",
                    "amount": 12345.65,
                    "currency": "EUR"
                }
            """

        when:
            def call = mockMvc.perform(post('/fundsTransfers')
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(payload))

        then:
            call.andExpect(status().isCreated())
    }

    @Unroll
    def "POST /fundsTransfers with missing properties should result in 400 BAD REQUEST"() {
        when:
            def call = mockMvc.perform(post('/fundsTransfers')
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(payload))

        then:
            call.andExpect(status().isBadRequest())

        where:
            payload << [
                """{ "recipientId": "${UUID.randomUUID()}", "amount": 12345.65, "currency": "EUR" }""",
                """{ "senderId": "${UUID.randomUUID()}", "amount": 12345.65, "currency": "EUR" }""",
                """{ "senderId": "${UUID.randomUUID()}", "recipientId": "${UUID.randomUUID()}", "currency": "EUR" }""",
                """{ "senderId": "${UUID.randomUUID()}", "recipientId": "${UUID.randomUUID()}", "amount": 12345.65 }"""
            ]
    }
}
