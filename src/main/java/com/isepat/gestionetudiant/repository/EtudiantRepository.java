package com.isepat.gestionetudiant.repository;

import com.isepat.gestionetudiant.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Verifie l'existence d'un matricule
    boolean existsByMatricule(String matricule);

    // Verifie l'existence d'un email
    boolean existsByEmail(String email);

    // Bonus : recherche d'un etudiant par son matricule
    Optional<Etudiant> findByMatricule(String matricule);

}
