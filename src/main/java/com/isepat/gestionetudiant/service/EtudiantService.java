package com.isepat.gestionetudiant.service;

import com.isepat.gestionetudiant.entity.Etudiant;
import com.isepat.gestionetudiant.exception.ChampObligatoireException;
import com.isepat.gestionetudiant.exception.EmailExistantException;
import com.isepat.gestionetudiant.exception.EtudiantNotFoundException;
import com.isepat.gestionetudiant.exception.MatriculeExistantException;
import com.isepat.gestionetudiant.repository.EtudiantRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    /**
     * Ajoute un nouvel etudiant apres verification des champs obligatoires
     * et de l'unicite du matricule / email.
     */
    public Etudiant ajouter(Etudiant etudiant) {
        validerChampsObligatoires(etudiant);

        if (etudiantRepository.existsByMatricule(etudiant.getMatricule())) {
            throw new MatriculeExistantException(etudiant.getMatricule());
        }
        if (etudiantRepository.existsByEmail(etudiant.getEmail())) {
            throw new EmailExistantException(etudiant.getEmail());
        }

        return etudiantRepository.save(etudiant);
    }

    /**
     * Modifie un etudiant existant. Si le matricule ou l'email changent,
     * on verifie qu'ils ne sont pas deja utilises par un AUTRE etudiant.
     */
    public Etudiant modifier(Long id, Etudiant donnees) {
        Etudiant existant = rechercher(id);

        validerChampsObligatoires(donnees);

        if (!existant.getMatricule().equals(donnees.getMatricule())
                && etudiantRepository.existsByMatricule(donnees.getMatricule())) {
            throw new MatriculeExistantException(donnees.getMatricule());
        }
        if (!existant.getEmail().equals(donnees.getEmail())
                && etudiantRepository.existsByEmail(donnees.getEmail())) {
            throw new EmailExistantException(donnees.getEmail());
        }

        existant.setMatricule(donnees.getMatricule());
        existant.setPrenom(donnees.getPrenom());
        existant.setNom(donnees.getNom());
        existant.setEmail(donnees.getEmail());
        existant.setDateNaissance(donnees.getDateNaissance());
        existant.setLieuNaissance(donnees.getLieuNaissance());
        existant.setNationalite(donnees.getNationalite());

        return etudiantRepository.save(existant);
    }

    /**
     * Supprime un etudiant par son id. Leve une exception 404 s'il n'existe pas.
     */
    public void supprimer(Long id) {
        Etudiant existant = rechercher(id);
        etudiantRepository.delete(existant);
    }

    /**
     * Recherche un etudiant par son id.
     */
    public Etudiant rechercher(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new EtudiantNotFoundException(id));
    }

    /**
     * Bonus : recherche un etudiant par son matricule.
     */
    public Etudiant rechercherParMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule)
                .orElseThrow(() -> new EtudiantNotFoundException(matricule));
    }

    /**
     * Liste tous les etudiants.
     * Bonus : si trierParNom = true, la liste est triee par nom (ordre alphabetique).
     */
    public List<Etudiant> lister(boolean trierParNom) {
        if (trierParNom) {
            return etudiantRepository.findAll(Sort.by(Sort.Direction.ASC, "nom"));
        }
        return etudiantRepository.findAll();
    }

    /**
     * Verifie manuellement que tous les champs obligatoires sont renseignes.
     * (Les annotations @Valid / @NotBlank ne sont pas encore utilisees a ce stade du cours.)
     */
    private void validerChampsObligatoires(Etudiant etudiant) {
        if (estVide(etudiant.getMatricule())) {
            throw new ChampObligatoireException("Le matricule est obligatoire.");
        }
        if (estVide(etudiant.getPrenom())) {
            throw new ChampObligatoireException("Le prenom est obligatoire.");
        }
        if (estVide(etudiant.getNom())) {
            throw new ChampObligatoireException("Le nom est obligatoire.");
        }
        if (estVide(etudiant.getEmail())) {
            throw new ChampObligatoireException("L'email est obligatoire.");
        }
        if (etudiant.getDateNaissance() == null) {
            throw new ChampObligatoireException("La date de naissance est obligatoire.");
        }
        if (estVide(etudiant.getLieuNaissance())) {
            throw new ChampObligatoireException("Le lieu de naissance est obligatoire.");
        }
        if (estVide(etudiant.getNationalite())) {
            throw new ChampObligatoireException("La nationalite est obligatoire.");
        }
    }

    private boolean estVide(String valeur) {
        return valeur == null || valeur.trim().isEmpty();
    }

}
