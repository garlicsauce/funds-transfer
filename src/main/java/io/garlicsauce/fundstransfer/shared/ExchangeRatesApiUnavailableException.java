package io.garlicsauce.fundstransfer.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExchangeRatesApiUnavailableException extends RuntimeException {

    private static final String MESSAGE = "Exchange rates API is now unavailable. Please try again later.";

    public ExchangeRatesApiUnavailableException() {
        super(MESSAGE);
    }
}
