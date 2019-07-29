package io.garlicsauce.fundstransfer.api;

import io.garlicsauce.fundstransfer.shared.AmountAndCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public class FundsTransferRequest implements Serializable {

    @NotNull
    private UUID senderId;
    @NotNull
    private UUID recipientId;
    @NotNull
    @Valid
    private AmountAndCurrency balance;
}
