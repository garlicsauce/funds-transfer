package io.garlicsauce.fundstransfer.domain;

import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import io.garlicsauce.fundstransfer.shared.Currency;

import java.math.BigDecimal;

public interface MoneyExchanger {

    AmountAndCurrency exchange(Currency currencyFrom, Currency currencyTo, BigDecimal amount);
}
