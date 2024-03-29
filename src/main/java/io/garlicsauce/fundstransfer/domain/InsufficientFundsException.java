package io.garlicsauce.fundstransfer.domain;

import io.garlicsauce.fundstransfer.shared.BadRequestException;

public class InsufficientFundsException extends BadRequestException {

    InsufficientFundsException() {
        super("Funds are insufficient to perform funds transfer");
    }
}
