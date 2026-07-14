package com.isepat.gestionetudiant.exception;

/**
 * Levee lorsque l'etudiant recherche n'existe pas (404 Not Found).
 */
public class EtudiantNotFoundException extends RuntimeException {

    public EtudiantNotFoundException(Long id) {
        super("Aucun etudiant trouve avec l'id " + id);
    }

    public EtudiantNotFoundException(String matricule) {
        super("Aucun etudiant trouve avec le matricule " + matricule);
    }

}
