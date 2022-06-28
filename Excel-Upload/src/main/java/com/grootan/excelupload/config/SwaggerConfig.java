package com.grootan.excelupload.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public static final String WEBURL = "https://www.grootan.com/";

    @Bean
    public OpenAPI excelServiceOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("Excel Upload Service")
                        .title("Excel to Database - V1")
                        .version("1.0.1")
                        .description("Rest APIs to Upload Excel data to MySQL Database")
                        .license(new License().name("Licensed To Grootan").url(WEBURL))
                        .termsOfService("api/swaggerTerms")
                )
                .schemaRequirement("Basic Auth", new SecurityScheme().scheme("basic")
                        .in(SecurityScheme.In.HEADER).type(SecurityScheme.Type.HTTP))
                ;
    }
}
