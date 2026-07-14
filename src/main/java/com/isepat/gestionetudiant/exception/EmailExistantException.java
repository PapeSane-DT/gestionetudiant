package com.isepat.gestionetudiant.exception;

/**
 * Levee lorsque l'email existe deja en base (409 Conflict).
 */
public class EmailExistantException extends RuntimeException {

    public EmailExistantException(String email) {
        super("L'email '" + email + "' existe deja.");
    }

}
