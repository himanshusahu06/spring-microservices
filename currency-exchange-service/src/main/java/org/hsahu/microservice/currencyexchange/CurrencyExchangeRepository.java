package org.hsahu.microservice.currencyexchange;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    // JPA will find best implementation for these methods
   Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
