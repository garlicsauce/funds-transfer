package io.garlicsauce.fundstransfer.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Wither
@Getter
@ToString
public class AmountAndCurrency implements Serializable {

    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final Currency currency;

    @JsonCreator
    public AmountAndCurrency(@JsonProperty("amount") BigDecimal amount,
                             @JsonProperty("currency") Currency currency) {
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
