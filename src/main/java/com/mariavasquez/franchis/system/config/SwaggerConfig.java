package com.mariavasquez.franchis.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.local}")
    private String localUrl;

    @Value("${swagger.server.prod}")
    private String prodUrl;

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @Bean
    public OpenAPI customOpenAPI() {
        List<Server> servers = new ArrayList<>();

        if ("local".equalsIgnoreCase(activeProfile)) {
            servers.add(new Server().url(localUrl).description("Local Server"));
        }

        if ("prod".equalsIgnoreCase(activeProfile) || "local".equalsIgnoreCase(activeProfile)) {
            servers.add(new Server().url(prodUrl).description("Producción"));
        }

        return new OpenAPI()
                .info(new Info()
                        .title("API Sistema de productos y franquicias")
                        .version("1.0.0")
                        .description("API para gestión de franquicias y productos")
                )
                .servers(servers);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .build();
    }
}
