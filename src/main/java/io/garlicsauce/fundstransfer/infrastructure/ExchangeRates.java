package io.garlicsauce.fundstransfer.infrastructure;

import io.garlicsauce.fundstransfer.shared.Currency;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Map;

@Value
public class ExchangeRates {

    private Map<Currency, BigDecimal> rates;
}
