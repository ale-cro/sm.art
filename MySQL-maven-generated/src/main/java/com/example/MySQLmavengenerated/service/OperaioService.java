package com.example.MySQLmavengenerated.service;

import com.example.MySQLmavengenerated.domain.Operaio;
import com.example.MySQLmavengenerated.domain.Timbratura;
import com.example.MySQLmavengenerated.repositories.OperaioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe dedicata al Service per Operaio
 * Contiene logica business per Operaio.
 */
@Service
public class OperaioService {

    @Autowired
    OperaioRepository operaioRepository;
    @Autowired
    EntityManager manager;

    /**
     * Metodo per tornare i dati assciati ad un Codice Fiscale
     * @param cf codice fiscare dell'operaio
     * @return Operaio, Contiene tutti i dati dell'operaio con il cf associato
     */
    public Operaio getOperaioByCF(String cf){
        Query query = manager.createQuery("Select o from Operaio o where o.cf='"+cf+"'",Operaio.class);
        return (Operaio) query.getSingleResult();
    }

    /**
     *
     * @return Lista di tutti operai presente nel DB
     */
    public List<Operaio> getAllOperai(){
        return new ArrayList<>(operaioRepository.findAll());
    }


    /**
     * Funzione per tornare lista di operai tramite paginazione
     * https://www.baeldung.com/jpa-pagination
     * @param page
     * @param size
     * @return Lista di operai
     */
    public List<Operaio> getOperaiWithPageParam(int page, int size){
        Query query = manager.createQuery("Select o From Operaio o");
        query.setFirstResult((page-1) * size);
        query.setMaxResults(size);
        return (List<Operaio>) query.getResultList();
    }

    /**
     * Metodo per inserire un nuovo Operaio all'interno del DB.
     * @param operaio dati dell'operaio da salvare nel DB
     */
    public void saveOperaio (Operaio operaio) {
        if (operaioRepository.existsOperaioByCf(operaio.getCf())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operaio already exists!");
        }else{
            operaioRepository.save(operaio);
        }
    }

    public void deleteOperaio(int cf){
        operaioRepository.deleteById(cf);
    }

    /**
     *
     * @return Lista di tutte le timbrature
     */
    public List<Timbratura> getTimbrature (){
        Query query = manager.createQuery("Select o.timbraturas from Operaio o",Operaio.class);
        return query.getResultList();
    }

    /**
     * Lista di Timbrature eseguite da un Codice Fiscale
     * @param cf
     * @return Lista di TImbrature
     */
    public List<Timbratura> getTimbratureFromCf (String cf){
        Query query = manager.createQuery("Select o.timbraturas from Operaio o where o.cf='"+cf+"'",Operaio.class);
        return query.getResultList();
    }

    /**
     * Timbrature eseguite in un periodo
     * @return Lista timbrature eseguite dal 0001/01/01 al 0001/02/01
     */
    public List<Timbratura> getTimbratureFromDate (){
        Query query = manager.createQuery("Select t from Timbratura t where t.entrata BETWEEN :startDate AND :endDate",
                Timbratura.class);
        query.setParameter("startDate", LocalDate.of(0001, 01, 01));
        query.setParameter("endDate", LocalDate.of(0001, 02, 01));
        return query.getResultList();
    }
}
