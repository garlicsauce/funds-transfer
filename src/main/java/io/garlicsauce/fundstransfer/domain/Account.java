package io.garlicsauce.fundstransfer.domain;

import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Account {

    private UUID ownerId;
    private AmountAndCurrency balance;

    public Account subtractMoney(AmountAndCurrency balance, MoneyExchanger moneyExchanger) {
        return this;
    }

    public Account addMoney(AmountAndCurrency balance, MoneyExchanger moneyExchanger) {
        return this;
    }
}
