package io.garlicsauce.fundstransfer.domain;

import io.garlicsauce.fundstransfer.shared.NotFoundException;

public class AccountNotFoundException extends NotFoundException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
