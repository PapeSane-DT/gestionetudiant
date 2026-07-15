package com.isepat.gestionetudiant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Intercepte les exceptions levees dans le controleur et les traduit
 * en reponses HTTP avec un corps {"code": ..., "msg": ...}.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChampObligatoireException.class)
    public ResponseEntity<ApiError> handleChampObligatoire(ChampObligatoireException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MatriculeExistantException.class)
    public ResponseEntity<ApiError> handleMatriculeExistant(MatriculeExistantException ex) {
        ApiError error = new ApiError(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(EmailExistantException.class)
    public ResponseEntity<ApiError> handleEmailExistant(EmailExistantException ex) {
        ApiError error = new ApiError(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(IdentifiantsInvalidesException.class)
    public ResponseEntity<ApiError> handleIdentifiantsInvalides(IdentifiantsInvalidesException ex) {
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(EtudiantNotFoundException.class)
    public ResponseEntity<ApiError> handleEtudiantNotFound(EtudiantNotFoundException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Filet de securite pour toute autre erreur imprevue
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Une erreur inattendue est survenue : " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
