package io.garlicsauce.fundstransfer.infrastructure

import io.garlicsauce.fundstransfer.shared.AmountAndCurrency
import io.garlicsauce.fundstransfer.shared.Currency
import spock.lang.Specification
import spock.lang.Subject

class ExchangeRatesMoneyExchangerUT extends Specification {

    def exchangeRatesApiClientMock = Mock(ExchangeRatesApiClient)

    @Subject
    def moneyExchanger = new ExchangeRatesMoneyExchanger(exchangeRatesApiClientMock)

    def "should not perform exchanging money when base and destined currency are equal"() {
        given:
            def currency = Currency.EUR

        and:
            def balance = new AmountAndCurrency(BigDecimal.ONE, currency)

        when:
            def result = moneyExchanger.exchange(balance, currency)

        then:
            result == balance

        and:
            0 * exchangeRatesApiClientMock.fetchExchangeRates(_ as Currency, _ as Currency)
    }

    def "should perform exchanging money when base and destined currency are different"() {
        given:
            def balance = new AmountAndCurrency(BigDecimal.ONE, Currency.EUR)

        when:
            def result = moneyExchanger.exchange(balance, Currency.PLN)

        then:
            1 * exchangeRatesApiClientMock.fetchExchangeRates(Currency.EUR, Currency.PLN) >> new ExchangeRates([(Currency.PLN) : BigDecimal.valueOf(4)])

        and:
            result.amount == BigDecimal.valueOf(4L)
            result.currency == Currency.PLN
    }
}
