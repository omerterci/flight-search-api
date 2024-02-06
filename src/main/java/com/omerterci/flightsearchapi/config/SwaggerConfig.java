package com.omerterci.flightsearchapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Flight Search API", version = "1.0", description = "Flight Search API documentation"))
public class SwaggerConfig {
}
