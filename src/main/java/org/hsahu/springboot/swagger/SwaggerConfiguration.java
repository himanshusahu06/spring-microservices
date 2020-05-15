package org.hsahu.springboot.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;

/**
 * Swagger 2.0 configuration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final Set<String> DEFAULT_PRODUCE_CONSUME_MEDIA_TYPE =
            new HashSet<>(Arrays.asList(APPLICATION_XML_VALUE, APPLICATION_JSON_VALUE));


    private static final ApiInfo DEFAULT_API_INFO  = new ApiInfoBuilder()
            .title("Customer Accounts User Service")
            .description("User service is centralize service to manage all kind of users present in the system. " +
                    "Any CRUD operation can be performed with these APIs.")
            .contact(new Contact("Himanshu Sahu", "www.google.com", "foo@baz.com"))
            .build();
    /**
     * swagger documentation are exposed at http://localhost:8080/v2/api-docs
     * swagger UI is exposed at http://localhost:8080/swagger-ui.html
     *
     * @return docket mvc interface
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCE_CONSUME_MEDIA_TYPE)
                .consumes(DEFAULT_PRODUCE_CONSUME_MEDIA_TYPE)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.hsahu.springboot.controller"))
                .build();
    }

    /**
     * fix for hateaos startup issue
     * https://stackoverflow.com/questions/58431876/spring-boot-2-2-0-spring-hateoas-startup-issue
     *
     * @return LinkDiscoverers
     */
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}
