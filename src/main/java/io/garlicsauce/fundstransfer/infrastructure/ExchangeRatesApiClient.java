package io.garlicsauce.fundstransfer.infrastructure;

import io.garlicsauce.fundstransfer.config.FeignConfig;
import io.garlicsauce.fundstransfer.shared.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ExchangeRatesApi", url = "https://api.exchangeratesapi.io", configuration = FeignConfig.class)
public interface ExchangeRatesApiClient {

    @GetMapping("/latest")
    ExchangeRates fetchExchangeRates(@RequestParam("base") Currency base,
                                     @RequestParam("symbols") Currency symbol);
}
