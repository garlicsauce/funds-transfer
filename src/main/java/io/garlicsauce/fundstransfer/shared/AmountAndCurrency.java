package io.garlicsauce.fundstransfer.shared;

import lombok.Value;
import lombok.experimental.Wither;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
@Wither
public class AmountAndCurrency implements Serializable {

    @NotNull
    private BigDecimal amount;
    @NotNull
    private Currency currency;

    public AmountAndCurrency(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    public AmountAndCurrency subtract(BigDecimal amountToSubtract) {
        return this.withAmount(this.amount.subtract(amountToSubtract));
    }

    public AmountAndCurrency add(BigDecimal amountToAdd) {
        return this.withAmount(this.amount.add(amountToAdd));
    }
}
