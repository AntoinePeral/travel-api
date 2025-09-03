# Travel Blog API – Résumé exhaustif

## Aperçu
Application REST en **Java/Spring Boot** pour gérer un blog de voyage : utilisateurs, carnets de voyage, étapes, articles, commentaires, médias et thèmes. Architecture en couches avec DTO/mappers, persistance JPA/Hibernate et sécurité JWT.

## Modules principaux

### 1. Utilisateurs
- Endpoints : `/users`
- Fonctionnalités : inscription, login, CRUD utilisateur.
- Mappage entité/DTO via `UserMapper`.
- Rôles : `ROLE_USER`, `ROLE_ADMIN`.

### 2. Carnets de voyage (Travel Diaries)
- Endpoints : `/travel-diaries`
- Champs : titre, description, confidentialité, publication, statut (`IN_PROGRESS`, `COMPLETED`), localisation.
- Relations : utilisateur, média de couverture, étapes.
- Services : `TravelDiaryService`.

### 3. Étapes
- Endpoints : `/steps`
- Champs : titre, description, période, géolocalisation, ville/pays/continent.
- Relations : carnet, médias, commentaires, thèmes.
- Services : `StepService`.

### 4. Articles
- Endpoints : `/articles`
- Gestion CRUD avec slug généré par `SlugUtil`.
- Relation avec l’utilisateur auteur.

### 5. Commentaires
- Endpoints : `/comments`
- Champs : contenu, dates, statut (`APPROVED`, `PENDING`, `REJECTED`).
- Relations : étape, utilisateur.
- Modération réservée aux administrateurs.

### 6. Médias
- Endpoints : `/medias`
- Types : `PHOTO`, `VIDEO`.
- Attachés à une étape ou à un carnet (couverture).
- Champ `isVisible` pour l’affichage.

### 7. Thèmes
- Endpoints : `/themes`
- Relations Many-to-Many avec les étapes.
- Service implémenté par `ThemeServiceImpl`.

### 8. Sécurité
- `SecurityConfig` : filtres JWT, CORS, règles d’accès par rôle.
- `JWTService` : génération et validation de tokens.

### 9. Exceptions
- `GlobalExceptionHandler` centralise la gestion des erreurs (404, 409, 403, validations, etc.).

### 10. Tests unitaires
- Tests pour controllers/services/repositories des modules `auth`, `media`, `user` afin de garantir les règles métier et les endpoints.

### 11. Configuration
- Profils Spring (`application.properties`, `application-local.properties`) et `.env` pour séparer local et Docker/prod.
- Variables sensibles (JWT, DB) stockées dans des fichiers `.env` non commités.

---

## Diagramme conceptuel (simplifié)

User ↔ TravelDiary ↔ Step ↔ {Media, Comment, Theme}  
Article ↔ User  
Media ↔ {Step | TravelDiary}  
Comment ↔ {Step, User}  
Theme ↔ Step

---

_Fin du résumé._
