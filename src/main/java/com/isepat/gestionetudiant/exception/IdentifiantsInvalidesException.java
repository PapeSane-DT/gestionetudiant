package com.isepat.gestionetudiant.exception;

/**
 * Levee lorsque l'email n'existe pas ou que le mot de passe est incorrect (401 Unauthorized).
 */
public class IdentifiantsInvalidesException extends RuntimeException {

    public IdentifiantsInvalidesException() {
        super("Email ou mot de passe incorrect.");
    }

}