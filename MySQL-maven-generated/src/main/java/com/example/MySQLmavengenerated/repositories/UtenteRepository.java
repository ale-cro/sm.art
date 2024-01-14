package com.example.MySQLmavengenerated.repositories;

import com.example.MySQLmavengenerated.domain.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaccia per definire metodi del repository dedicato all'Utente
 * Estende l'interfaccia JpaRepository (<a href="https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html">...</a>),
 * JpaRepository contiene tutti i metodi definiti all'interno delle interfacce CrudRepository e PagingandsortingRepository.
 */
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    /**
     * Metodo per definire se l'utente è registrato all'interno del db.
     * @param nome : nome dell'utente da cercare
     * @param password : password associata all'utente
     * @return bool a seconda se l'utente è presente nel DB con la password associata
     */
    boolean findUtenteByNomeAndPassword(String nome, String password);

    /**
     * Metodo per tornare i dati di un Utente.
     * @param nome Nome dell'utente da cercare
     * @return Utente con il nome associato
     */
    Utente findByNome(String nome);

    /**
     * Metodo per controllare se il nome utente è presente nel DB.
     * @param nome Nome utente da cercare.
     * @return bool se è presente il nome utente nel DB.
     */
    boolean existsUtenteByNome(String nome);



}
