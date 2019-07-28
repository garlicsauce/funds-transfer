package io.garlicsauce.fundstransfer.infrastructure;

import io.garlicsauce.fundstransfer.domain.MoneyExchanger;
import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import io.garlicsauce.fundstransfer.shared.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeRatesMoneyExchanger implements MoneyExchanger {

    @Override
    public AmountAndCurrency exchange(Currency currencyFrom, Currency currencyTo, BigDecimal amount) {
        return null;
    }
}
