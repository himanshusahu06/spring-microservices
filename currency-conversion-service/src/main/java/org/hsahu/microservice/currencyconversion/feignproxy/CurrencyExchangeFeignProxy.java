package org.hsahu.microservice.currencyconversion.feignproxy;

import org.hsahu.microservice.currencyconversion.ConvertedCurrency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client is used to invoke rest services built on spring web.
 * Ribbon client is used for client side load distribution, both ribbon and feign must have exact
 * same service name as registered in {spring.application.name} property of target service's
 * application.properties file
 */
//@FeignClient(name = "currency-exchange-service") use feign to invoke rest services, name must match with {spring.application.name} property of target service
//@RibbonClient(name = "currency-exchange-service") // use ribbon for load distribution, name must match with {spring.application.name} property of target service
@FeignClient(name = "netfilx-zuul-api-gateway-server") // connect to zuul server instead of directly calling the microservices
// zuul handles load distribution as well so ribbon is useless unless you've multiple instances of netflix zuul api gateway server
public interface CurrencyExchangeFeignProxy {

    @GetMapping("/currency-exchange-service/currency-exchange/from/{source_currency}/to/{destination_currency}")
    ConvertedCurrency currencyExchange(
            @PathVariable("source_currency") String sourceCurrency,
            @PathVariable("destination_currency") String destinationCurrency);
}
