package com.example.MySQLmavengenerated.service;

import com.example.MySQLmavengenerated.domain.Utente;
import com.example.MySQLmavengenerated.passwordencoding.BCryptEncoder;
import com.example.MySQLmavengenerated.repositories.UtenteRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    EntityManager manager;

    private final BCryptEncoder bcPsw = new BCryptEncoder();

    public ArrayList<Utente> getUtenti() {
        return (ArrayList<Utente>) utenteRepository.findAll();
    }

    /*Il metodo registerUser, oltre a richiamare il metodo save del repository, codifica la password prima di salvare su db.*/

    /**
     * Metodo per registare nuovo utente
     * @param utente
     * @return
     */
    public Utente registraUtente(Utente utente){
        if (utenteRepository.existsUtenteByNome(utente.getNome())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken!");
        }else {
            utente.setPassword(bcPsw.encode(utente.getPassword()));
            utente.setNome(utente.getNome());
            System.out.println("password criptata:"+utente.getPassword());
            return utenteRepository.save(utente);
        }
    }


    public Utente loginUtente(Utente utente) {
        if (utenteRepository.findByNome(utente.getNome()) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username non registrato");
        }
        String pass = utenteRepository.findByNome(utente.getNome()).getPassword();
        //metodo di match per confrontare se pass bcryptata è uguale alla pass in chiaro
        if (!bcPsw.matches(utente.getPassword(), pass)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password incorrect");
        }
        return utente;
    }

    /**
     * Modifica password di un utente
     * @param utente
     * @param newPass
     * @return
     */
    public Utente cambioPassword(Utente utente, String newPass){
        if(utenteRepository.findUtenteByNomeAndPassword(utente.getNome(), utente.getPassword())){
            utente.setPassword(newPass);
            utenteRepository.save(utente);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username o password errati");
        }
        return utente;
    }

    /**
     * Modifica dei dati (nome, email,password) di un utente registrato
     * @param utente
     * @param Iduser
     * @return
     */
    /*Prima si trova l'utente tramite id e poi si insieriscono i nuovi dati*/
    public Utente updateUtente(Utente utente, int Iduser){
        return utenteRepository.findById(Iduser)
                .map(nuovoUtente -> {
                    nuovoUtente.setNome(utente.getNome());
                    nuovoUtente.setEmail(utente.getEmail());
                    nuovoUtente.setPassword(utente.getPassword());
                    return utenteRepository.save(nuovoUtente);
                })
                .orElseGet(() -> {
                    utente.setIdutente(Iduser);
                    return utenteRepository.save(utente);
                });
    }

}
