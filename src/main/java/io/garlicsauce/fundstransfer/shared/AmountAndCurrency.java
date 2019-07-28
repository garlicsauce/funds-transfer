package io.garlicsauce.fundstransfer.shared;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class AmountAndCurrency implements Serializable {

    @NotNull
    private BigDecimal amount;
    @NotNull
    private Currency currency;
}
