package com.isepat.gestionetudiant.exception;

/**
 * Levee lorsque le matricule existe deja en base (409 Conflict).
 */
public class MatriculeExistantException extends RuntimeException {

    public MatriculeExistantException(String matricule) {
        super("Le matricule '" + matricule + "' existe deja.");
    }

}
