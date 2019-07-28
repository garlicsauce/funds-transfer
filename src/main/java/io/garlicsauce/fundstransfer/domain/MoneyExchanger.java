package io.garlicsauce.fundstransfer.domain;

import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import io.garlicsauce.fundstransfer.shared.Currency;

public interface MoneyExchanger {

    AmountAndCurrency exchange(AmountAndCurrency balance, Currency currencyTo);
}
