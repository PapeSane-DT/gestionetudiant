package com.isepat.gestionetudiant.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI gestionEtudiantOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestion des Etudiants - ISEP-AT")
                        .version("1.0.0")
                        .description("API REST CRUD permettant de gerer les etudiants de l'ISEP-AT : "
                                + "ajout, modification, suppression, recherche et listing, "
                                + "avec controles metiers manuels et codes HTTP adaptes. "
                                + "Securisee par authentification JWT.")
                        .contact(new Contact()
                                .name("Dr Samba SIDIBE")
                                .email("ssidibe@ept.edu.sn")))
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME, new SecurityScheme()
                                .name(SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }

}