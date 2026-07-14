package com.isepat.gestionetudiant.controller;

import com.isepat.gestionetudiant.entity.Etudiant;
import com.isepat.gestionetudiant.exception.ApiError;
import com.isepat.gestionetudiant.service.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
@Tag(name = "Etudiants", description = "Gestion des etudiants de l'ISEP-AT (CRUD)")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    @Operation(summary = "Ajouter un etudiant",
            description = "Cree un nouvel etudiant apres verification des champs obligatoires "
                    + "et de l'unicite du matricule et de l'email.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Etudiant cree avec succes",
                    content = @Content(schema = @Schema(implementation = Etudiant.class))),
            @ApiResponse(responseCode = "400", description = "Un champ obligatoire est manquant",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Le matricule ou l'email existe deja",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<Etudiant> ajouter(@RequestBody Etudiant etudiant) {
        Etudiant cree = etudiantService.ajouter(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(cree);
    }

    @GetMapping
    @Operation(summary = "Lister les etudiants",
            description = "Retourne la liste de tous les etudiants. "
                    + "Bonus : parametre 'tri=nom' pour trier par nom en ordre alphabetique.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste recuperee avec succes")
    })
    public ResponseEntity<List<Etudiant>> lister(
            @Parameter(description = "Mettre 'nom' pour trier la liste par nom (bonus)")
            @RequestParam(required = false) String tri) {
        boolean trierParNom = "nom".equalsIgnoreCase(tri);
        return ResponseEntity.ok(etudiantService.lister(trierParNom));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un etudiant par id",
            description = "Retourne l'etudiant correspondant a l'identifiant technique fourni.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Etudiant trouve",
                    content = @Content(schema = @Schema(implementation = Etudiant.class))),
            @ApiResponse(responseCode = "404", description = "Aucun etudiant trouve avec cet id",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<Etudiant> rechercher(
            @Parameter(description = "Identifiant technique de l'etudiant") @PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.rechercher(id));
    }

    @GetMapping("/matricule/{matricule}")
    @Operation(summary = "Bonus : rechercher un etudiant par matricule",
            description = "Retourne l'etudiant correspondant au matricule fourni.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Etudiant trouve",
                    content = @Content(schema = @Schema(implementation = Etudiant.class))),
            @ApiResponse(responseCode = "404", description = "Aucun etudiant trouve avec ce matricule",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<Etudiant> rechercherParMatricule(
            @Parameter(description = "Matricule de l'etudiant") @PathVariable String matricule) {
        return ResponseEntity.ok(etudiantService.rechercherParMatricule(matricule));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un etudiant",
            description = "Met a jour les informations d'un etudiant existant, "
                    + "avec les memes controles metiers que pour l'ajout.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Etudiant modifie avec succes",
                    content = @Content(schema = @Schema(implementation = Etudiant.class))),
            @ApiResponse(responseCode = "400", description = "Un champ obligatoire est manquant",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Aucun etudiant trouve avec cet id",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Le matricule ou l'email existe deja",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<Etudiant> modifier(
            @Parameter(description = "Identifiant technique de l'etudiant") @PathVariable Long id,
            @RequestBody Etudiant etudiant) {
        return ResponseEntity.ok(etudiantService.modifier(id, etudiant));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un etudiant",
            description = "Supprime definitivement l'etudiant correspondant a l'identifiant fourni.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Etudiant supprime avec succes"),
            @ApiResponse(responseCode = "404", description = "Aucun etudiant trouve avec cet id",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<Void> supprimer(
            @Parameter(description = "Identifiant technique de l'etudiant") @PathVariable Long id) {
        etudiantService.supprimer(id);
        return ResponseEntity.noContent().build();
    }

}
