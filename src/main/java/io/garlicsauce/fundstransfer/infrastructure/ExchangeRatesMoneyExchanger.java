package io.garlicsauce.fundstransfer.infrastructure;

import io.garlicsauce.fundstransfer.domain.MoneyExchanger;
import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import io.garlicsauce.fundstransfer.shared.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRatesMoneyExchanger implements MoneyExchanger {

    private final ExchangeRatesApiClient exchangeRatesApiClient;

    @Override
    public AmountAndCurrency exchange(AmountAndCurrency balance, Currency currencyTo) {
        if (balance.getCurrency().equals(currencyTo)) {
            return balance;
        }

        ExchangeRates exchangeRates = exchangeRatesApiClient.fetchExchangeRates(balance.getCurrency(), currencyTo);
        return new AmountAndCurrency(
                balance.getAmount().multiply(exchangeRates.getRates().get(currencyTo)),
                currencyTo);
    }
}
