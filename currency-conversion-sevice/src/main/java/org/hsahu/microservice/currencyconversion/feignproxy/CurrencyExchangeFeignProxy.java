package org.hsahu.microservice.currencyconversion.feignproxy;

import org.hsahu.microservice.currencyconversion.ConvertedCurrency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeFeignProxy {

    @GetMapping("/currency-exchange/from/{source_currency}/to/{destination_currency}")
    ConvertedCurrency currencyExchange(
            @PathVariable("source_currency") String sourceCurrency,
            @PathVariable("destination_currency") String destinationCurrency);
}
