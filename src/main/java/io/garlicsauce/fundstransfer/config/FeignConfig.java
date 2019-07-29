package io.garlicsauce.fundstransfer.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import io.garlicsauce.fundstransfer.shared.ExchangeRatesApiUnavailableException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static feign.FeignException.errorStatus;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            if (response.status() == 503) {
                throw new ExchangeRatesApiUnavailableException();
            }

            return errorStatus(methodKey, response);
        };
    }
}
