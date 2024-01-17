# sm.art
BackEnd dell'applicativo. Viene utilizzato Maven.

Prima di clonare il progetto bisogna assiscurarsi di avere spring boot installato (https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.installing).

 ./mvnw spring-boot:run su terminale per eseguire applicativo. Ctrl + c per terminare
 
 Nota : Assicurarsi che il server sia online. 
 Su windows 11 andare su Servizi e controllare che MySQL80 sia in esecuzione.
 
## Struttura applicatibo
domain : Contiene classi che descrivono il dominio dei dati dell'applicativo. Le classi sono state generate tramite JPA 
Le classi sono Operaio, Timbratura, Utente e Ruolo

dto : Contiene classe UtenteDTO

passwordencoding : Contiene classe per eseguire hashing delle password usando come encoder BCrypt (https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt) 

repositories : Contiene interfacce per eseguire operazioni su una raccolta di oggetti. Entrambe le interfacce estendono JpaRepository.

web : Contiene  MainController per definire gli endpoint dell'applicativo e  SecurityConfig per eseguire la profilazione degli utenti e per gestire i livelli di accesso. Al momento non vengono gestiti i livelli di accesso, quindi tutti possono accedere alle risorse definite in MainController.
