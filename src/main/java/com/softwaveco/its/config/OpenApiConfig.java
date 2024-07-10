package com.softwaveco.its.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Traffic Service API", version = "v1"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {

    private static final String BACKEND_URL = "http://localhost:8080";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.softwaveco.its")
                .addOpenApiCustomiser(openApi -> {
                    io.swagger.v3.oas.models.servers.Server server = new io.swagger.v3.oas.models.servers.Server().url(BACKEND_URL);
                    openApi.addServersItem(server);
                    openApi.servers(List.of(server));
                })
                .build();
    }
}