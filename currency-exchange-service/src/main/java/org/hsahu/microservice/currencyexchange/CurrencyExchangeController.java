package org.hsahu.microservice.currencyexchange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{source_currency}/to/{destination_currency}")
    public CurrencyExchange currencyExchange(
            @PathVariable("source_currency") String sourceCurrency,
            @PathVariable("destination_currency") String destinationCurrency) {
        String from = sourceCurrency.toUpperCase();
        String to = destinationCurrency.toUpperCase();
        if (from.trim().length() != 3) {
            throw  new IllegalArgumentException("source currency must have at least 3 characters.");
        }
        if (to.trim().length() != 3) {
            throw  new IllegalArgumentException("source currency must have at least 3 characters.");
        }

        Optional<CurrencyExchange> currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (!currencyExchange.isPresent()) {
            throw new IllegalArgumentException("given combination of currency is not supported");
        }
        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port")));
        currencyExchange.get().setPort(port);
        log.info("{}", currencyExchange);
        return currencyExchange.get();
    }
}
