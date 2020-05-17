package org.hsahu.microservice.limitsservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * class to load group of properties from application.properties file where
 * properties are grouped by prefix defined inside ConfigurationProperties
 * annotation (hierarchical properties that all have the same prefix).
 * <p>
 * https://www.baeldung.com/configuration-properties-in-spring-boot
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "limits-service")
public class Configuration {
    private int minimum;
    private int maximum;
}
