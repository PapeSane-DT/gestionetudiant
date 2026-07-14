package com.isepat.gestionetudiant.exception;

/**
 * Levee lorsqu'un champ obligatoire est manquant ou vide (400 Bad Request).
 */
public class ChampObligatoireException extends RuntimeException {

    public ChampObligatoireException(String message) {
        super(message);
    }

}
