package com.isepat.gestionetudiant.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Format standard du corps de reponse en cas d'erreur.
 * Exemple : {"code": 400, "msg": "Le matricule est obligatoire."}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private int code;
    private String msg;

}
