package io.garlicsauce.fundstransfer.domain

import io.garlicsauce.fundstransfer.infrastructure.ExchangeRatesMoneyExchanger
import io.garlicsauce.fundstransfer.shared.AmountAndCurrency
import io.garlicsauce.fundstransfer.shared.Currency
import spock.lang.Specification

class AccountUT extends Specification {

    def moneyExchangerMock = Mock(ExchangeRatesMoneyExchanger)

    def "should throw exception when trying to subtract more money than balance of account"() {
        given:
            def account = new Account(UUID.randomUUID(), new AmountAndCurrency(BigDecimal.ONE, Currency.EUR))

        and:
            def transferBalance = new AmountAndCurrency(BigDecimal.TEN, Currency.EUR)

        and:
            moneyExchangerMock.exchange(transferBalance, account.balance.currency) >> transferBalance

        when:
            account.subtractMoney(transferBalance, moneyExchangerMock)

        then:
            thrown(InsufficientFundsException)
    }

    def "should subtract amount of money from account"() {
        given:
            def account = new Account(UUID.randomUUID(), new AmountAndCurrency(BigDecimal.TEN, Currency.EUR))

        and:
            def transferBalance = new AmountAndCurrency(BigDecimal.ONE, Currency.EUR)

        and:
            moneyExchangerMock.exchange(transferBalance, account.balance.currency) >> transferBalance

        when:
            def result = account.subtractMoney(transferBalance, moneyExchangerMock)

        then:
            result.balance.amount == BigDecimal.valueOf(9)
    }

    def "should add money to account balance"() {
        given:
            def account = new Account(UUID.randomUUID(), new AmountAndCurrency(BigDecimal.TEN, Currency.EUR))

        and:
            def transferBalance = new AmountAndCurrency(BigDecimal.ONE, Currency.EUR)

        and:
            moneyExchangerMock.exchange(transferBalance, account.balance.currency) >> transferBalance

        when:
            def result = account.addMoney(transferBalance, moneyExchangerMock)

        then:
            result.balance.amount == BigDecimal.valueOf(11)
    }
}
