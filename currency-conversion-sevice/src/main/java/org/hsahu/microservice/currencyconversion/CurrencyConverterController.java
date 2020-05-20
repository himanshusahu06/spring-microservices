package org.hsahu.microservice.currencyconversion;

import lombok.extern.slf4j.Slf4j;
import org.hsahu.microservice.currencyconversion.feignproxy.CurrencyExchangeFeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
public class CurrencyConverterController {

    @Autowired
    private CurrencyExchangeFeignProxy currencyExchangeServiceFeignProxy;

    @GetMapping("/currency-converter/from/{from_currency}/to/{to_currency}/quantity/{quantity}")
    public ConvertedCurrency currencyConverter(
            @PathVariable("from_currency") String fromCurrency,
            @PathVariable("to_currency") String toCurrency,
            @PathVariable("quantity") BigDecimal quantity) {
        String from = fromCurrency.toUpperCase();
        String to = toCurrency.toUpperCase();
        if (from.trim().length() != 3) {
            throw  new IllegalArgumentException("source currency must have at least 3 characters.");
        }
        if (to.trim().length() != 3) {
            throw  new IllegalArgumentException("source currency must have at least 3 characters.");
        }
        // use currency  exchange feign proxy to invoke the currency exchange APIs
        ConvertedCurrency convertedCurrency = currencyExchangeServiceFeignProxy.currencyExchange(from, to);
        convertedCurrency.setQuantity(quantity);
        convertedCurrency.setTotalCalculatedAmount(ConvertedCurrency.calculateAmount(convertedCurrency));
        log.info("{}", convertedCurrency);
        return convertedCurrency;
    }
}
