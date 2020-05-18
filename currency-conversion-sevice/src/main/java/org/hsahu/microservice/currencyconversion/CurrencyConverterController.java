package org.hsahu.microservice.currencyconversion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConverterController {

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

        Map<String, String> variables = new HashMap<String, String>() {{
            put("from", from);
            put("to", to);
        }};
        ConvertedCurrency convertedCurrency = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", ConvertedCurrency.class, variables)
                .getBody();
        assert convertedCurrency != null;
        convertedCurrency.setQuantity(quantity);
        convertedCurrency.setTotalCalculatedAmount(ConvertedCurrency.calculateAmount(convertedCurrency));
        return convertedCurrency;
    }
}
