package com.example.MySQLmavengenerated.repositories;

import com.example.MySQLmavengenerated.domain.Operaio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaccia per definire metodi del repository dedicato all'Operaio
 * Estende l'interfaccia JpaRepository (<a href="https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html">...</a>),
 * JpaRepository contiene tutti i metodi definiti all'interno delle interfacce CrudRepository e PagingandsortingRepository.
 * */
@Repository
public interface OperaioRepository extends JpaRepository<Operaio, Integer> {

    /* https://www.baeldung.com/spring-data-exists-query
        Grazie al Spring’s derived query method si possono formulare query come nome di una funzione.
        Attenzione: queste query devono seguire una sintassi precisa.*/

    /**
     * Metodo per controllare l'esistenza dell'operaio all'interno del DB tramite Codice Fiscale
     * @param cf Codice Fiscale dell'operaio
     * @return bool
     */
    boolean existsOperaioByCf(String cf);
}
