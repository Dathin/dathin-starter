package io.github.dathin.boot.dathinstarteropenapi.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private final String moduleName;
    private final String apiVersion;

    public OpenApiConfig() {
        this.moduleName = "pedro";
        this.apiVersion = "v1";
    }

    @Bean
    @ConditionalOnBean
    public OpenAPI customOpenAPI() {
        var securitySchemeName = "bearerAuth";
        var apiTitle = String.format("%s API", moduleName);
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                ).info(new Info().title(apiTitle).version(apiVersion));
    }
}