# Gestion Etudiant - API REST (ISEP-AT)

API REST CRUD de gestion des etudiants, developpee avec Spring Boot dans le cadre du TP
"Developpement d'API REST CRUD avec Spring Boot" (Dr Samba SIDIBE).

## 1. Prerequis

- Java 17
- Maven 3.9+
- MySQL (via XAMPP en local, ou une base cloud en prod)

## 2. Architecture (couches)

```
com.isepat.gestionetudiant
├── entity/        -> Etudiant (entite JPA)
├── repository/     -> EtudiantRepository (Spring Data JPA)
├── service/         -> EtudiantService (logique metier + controles manuels)
├── controller/      -> EtudiantController (endpoints REST + Swagger)
├── exception/       -> Exceptions custom + GlobalExceptionHandler + ApiError
└── config/          -> OpenApiConfig (infos Swagger)
```

## 3. Configuration des profils

- **dev** (par defaut) : base `isepat_dev` en local (XAMPP, user root sans mot de passe)
- **prod** : base `isepat_cloud`, lue via variables d'environnement `DB_URL`,
  `DB_USERNAME`, `DB_PASSWORD` (utile pour un deploiement type Railway)

Pour changer de profil, modifier `spring.profiles.active` dans `application.properties`,
ou lancer avec :
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Avant de lancer en local, cree la base dans phpMyAdmin/XAMPP (ou laisse
`createDatabaseIfNotExist=true` faire le travail) :
```sql
CREATE DATABASE IF NOT EXISTS isepat_dev;
```

## 4. Lancer le projet

```bash
mvn clean install
mvn spring-boot:run
```

L'API demarre sur `http://localhost:8080`.

## 5. Swagger UI

Une fois l'application lancee :
- Swagger UI : http://localhost:8080/swagger-ui.html
- JSON OpenAPI : http://localhost:8080/v3/api-docs

## 6. Endpoints

| Methode | URL                          | Description                          |
|---------|------------------------------|---------------------------------------|
| POST    | /etudiants                   | Ajouter un etudiant                   |
| GET     | /etudiants                   | Lister les etudiants                  |
| GET     | /etudiants?tri=nom           | Bonus : lister tries par nom          |
| GET     | /etudiants/{id}               | Rechercher un etudiant par id          |
| GET     | /etudiants/matricule/{mat}    | Bonus : rechercher par matricule       |
| PUT     | /etudiants/{id}               | Modifier un etudiant                   |
| DELETE  | /etudiants/{id}               | Supprimer un etudiant                  |

## 7. Codes HTTP geres

| Situation                     | Code             |
|--------------------------------|------------------|
| Creation reussie                | 201 Created       |
| Modification reussie            | 200 OK            |
| Suppression reussie             | 204 No Content    |
| Champ obligatoire manquant      | 400 Bad Request   |
| Matricule deja existant         | 409 Conflict      |
| Email deja existant             | 409 Conflict      |
| Etudiant introuvable            | 404 Not Found     |

Format d'erreur standard :
```json
{ "code": 400, "msg": "Le matricule est obligatoire." }
```

## 8. Tests

Le fichier `requests.http` a la racine contient les 6 scenarios de test demandes
dans le sujet (ajout, matricule dupliquue, email dupliquue, champ manquant,
recherche inexistante, suppression), plus les endpoints bonus. Il peut etre
execute directement depuis VS Code (extension REST Client) ou IntelliJ.

Sinon, tout peut etre teste directement depuis Swagger UI comme demande dans
la partie X du sujet (captures d'ecran a joindre au rendu).

## 9. Points bonus implementes

- Recherche d'un etudiant par matricule : `GET /etudiants/matricule/{matricule}`
- Tri de la liste par nom (ordre alphabetique) : `GET /etudiants?tri=nom`
