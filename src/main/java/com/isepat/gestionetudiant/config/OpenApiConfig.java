package com.isepat.gestionetudiant.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionEtudiantOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestion des Etudiants - ISEP-AT")
                        .version("1.0.0")
                        .description("API REST CRUD permettant de gerer les etudiants de l'ISEP-AT : "
                                + "ajout, modification, suppression, recherche et listing, "
                                + "avec controles metiers manuels et codes HTTP adaptes.")
                        .contact(new Contact()
                                .name("Dr Samba SIDIBE")
                                .email("ssidibe@ept.edu.sn")));
    }

}
