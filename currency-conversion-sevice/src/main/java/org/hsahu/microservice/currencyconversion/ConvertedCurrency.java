package org.hsahu.microservice.currencyconversion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ConvertedCurrency {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionFactor;
    @Setter
    private BigDecimal quantity;
    @Setter
    private BigDecimal totalCalculatedAmount;
    private int port;

    public static BigDecimal calculateAmount(ConvertedCurrency convertedCurrency) {
        return convertedCurrency.getConversionFactor().multiply(convertedCurrency.getQuantity());
    }
}
