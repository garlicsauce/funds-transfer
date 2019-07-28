package io.garlicsauce.fundstransfer.domain;

import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class Account {

    private UUID ownerId;
    private BigDecimal balance;
    private Currency currency;

    public void subtractMoney(AmountAndCurrency balance, MoneyExchanger moneyExchanger) {

    }

    public void addMoney(AmountAndCurrency balance, MoneyExchanger moneyExchanger) {

    }
}
