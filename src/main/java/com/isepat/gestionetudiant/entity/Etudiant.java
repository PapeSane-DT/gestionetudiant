package com.isepat.gestionetudiant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entite representant un etudiant de l'ISEP-AT.
 * Remarque : l'age n'est pas stocke en base, il sera calcule cote front-end
 * a partir de la dateNaissance.
 */
@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String matricule;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Column(nullable = false)
    private String lieuNaissance;

    @Column(nullable = false)
    private String nationalite;

}
