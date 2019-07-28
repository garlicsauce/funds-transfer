package io.garlicsauce.fundstransfer.api;

import io.garlicsauce.fundstransfer.shared.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FundsTransferRequest implements Serializable {

    @NotNull
    private UUID senderId;
    @NotNull
    private UUID recipientId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Currency currency;
}
