package io.garlicsauce.fundstransfer.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ExchangeRatesApiUnavailableException extends RuntimeException {

    public ExchangeRatesApiUnavailableException() {
        super("Exchange rates API is now unavailable. Please try again later.");
    }
}
