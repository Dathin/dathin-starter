package io.github.dathin.boot.dathinstarteropenapi.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OpenApiConfigProperties.class)
public class OpenApiConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiConfig.class);

    @Bean
    public OpenAPI customOpenAPI(OpenApiConfigProperties openApiConfigProperties) {
        var openApi = new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("default"))
                .components(
                        new Components()
                                .addSecuritySchemes("default",
                                        new SecurityScheme()
                                                .name("default")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                ).info(new Info().title(openApiConfigProperties.getName()).version(openApiConfigProperties.getVersion()));
        LOGGER.info("Open API ui available at /swagger-ui/index.html");
        return openApi;
    }
}