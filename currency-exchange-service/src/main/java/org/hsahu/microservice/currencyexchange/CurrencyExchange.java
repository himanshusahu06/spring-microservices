package org.hsahu.microservice.currencyexchange;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchange {
    @Id
    private Long id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionFactor;
    @Setter
    @Transient
    private int port; // to distinguish from where the response is coming
}
