package io.garlicsauce.fundstransfer.domain;

import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Account {

    private UUID ownerId;
    private AmountAndCurrency balance;

    public Account subtractMoney(AmountAndCurrency balance, MoneyExchanger moneyExchanger) {
        AmountAndCurrency transferBalanceAfterCurrencyExchange =
                moneyExchanger.exchange(balance, this.balance.getCurrency());

        if (this.balance.getAmount().compareTo(transferBalanceAfterCurrencyExchange.getAmount()) < 0) {
            throw new InsufficientFundsException();
        }

        this.balance = this.balance.subtract(transferBalanceAfterCurrencyExchange.getAmount());

        return this;
    }

    public Account addMoney(AmountAndCurrency balance, MoneyExchanger moneyExchanger) {
        AmountAndCurrency transferBalanceAfterCurrencyExchange =
                moneyExchanger.exchange(balance, this.balance.getCurrency());

        this.balance = this.balance.add(transferBalanceAfterCurrencyExchange.getAmount());

        return this;
    }
}
