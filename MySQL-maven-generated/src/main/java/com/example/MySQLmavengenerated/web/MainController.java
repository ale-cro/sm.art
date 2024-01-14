package com.example.MySQLmavengenerated.web;

import com.example.MySQLmavengenerated.domain.Operaio;
import com.example.MySQLmavengenerated.domain.Timbratura;
import com.example.MySQLmavengenerated.domain.Utente;
import com.example.MySQLmavengenerated.service.OperaioService;
import com.example.MySQLmavengenerated.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller per gestire richieste HTTP.
 * In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller.
 * These components are identified by the @RestController annotation.
 *
 * @RestController indicates that the data returned by each method will be written straight into the response body instead of rendering a template
 */

@RestController
@RequestMapping(path = "/demo")
public class MainController {

    /**
     * Service contenenti le operazioni da eseguire all'interno dei Repository Operaio e Utente
     */
    @Autowired
    private OperaioService opService;
    @Autowired
    private UtenteService utenteService;

    /**
     * Endpoint per ritornare la lista degli utenti registrati
     *
     * @return lista utenti registrati
     */
    @GetMapping(path = "/gestione_utenti")
    public @ResponseBody ArrayList<Utente> getUtenti() {
        return utenteService.getUtenti();
    }

    ;

    /**
     * Endpoint dedicato alla modifica dei dati di un utente tramite il suo Idutente
     *
     * @param u        Oggetto Utente che contiene i nuovi dati.
     * @param Idutente ID dell'utente che si vuole modificare
     * @return Utente con i dati aggiornati
     */
    @PutMapping(path = "/modifica_utente/{Idutente}")
    public Utente updateUtente(@RequestBody Utente u, @PathVariable("Idutente") Integer Idutente) {
        return utenteService.updateUtente(u, Idutente);
    }

    /**
     * Endpoint per aggiungere un nuovo operaio all'interno del DB.
     *
     * @param op
     * @return
     */
    @PostMapping(path = "/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    // Also note, that Spring only uses @ResponseStatus, when the marked method completes successfully  (without throwing an Exception).
    public @ResponseBody Operaio addOperaio(@RequestBody Operaio op) {
        opService.saveOperaio(op);
        return op;
    }

    /**
     * Metodo per registrare un nuovo utente nel DB
     *
     * @param users Utente
     * @return
     */
    @PostMapping(path = "/register")
    public Utente registerUser(@RequestBody Utente users) {
        return utenteService.registraUtente(users);
    }

    @PostMapping(path = "/login")
    public Utente loginUser(@RequestBody Utente user, HttpServletRequest request, HttpServletResponse response) {
        return utenteService.loginUtente(user);
    }

    /**
     * Endpoint dedicato alla modifica della password di un nuovo utente
     *
     * @param user          Utente a cui so vuole cambiare password
     * @param nuovaPassword la Nuova Password da associare all'utente
     * @return
     */
    @PostMapping(path = "/changePass")
    public Utente changePassword(@RequestBody Utente user, @RequestBody String nuovaPassword) {
        return utenteService.cambioPassword(user, nuovaPassword);
    }

    /**
     * Endpoint che ritorna tutti gli Operai presenti nel DB
     *
     * @return
     */
    @GetMapping(path = "/all_op")
    public @ResponseBody Iterable<Operaio> getAllOperai() {
        return opService.getAllOperai();
    }

    // http://localhost:8080/demo/param?page=1&size=3
    /**
     * Endpoint per tornare gli operai paginati
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/param")
    public @ResponseBody Iterable<Operaio> getOperaiWithPageParam(@RequestParam int page, @RequestParam int size) {
        return opService.getOperaiWithPageParam(page, size);
    }


    //http://localhost:8080/demo/timbro/abc
    /**
     * Endpoint per tornare timbrature eseguite da un certo Operaio tramite Codice Fiscale
     *
     * @param cf
     * @return
     */
    @GetMapping(path = "/timbro/{cf}")
    public @ResponseBody List<Timbratura> getTimbratureFromCf(@PathVariable("cf") String cf) {
        return opService.getTimbratureFromCf(cf);
    }

    /*Ritorna Timbrature fatte in una data
    @GetMapping(path = "/timbro/date")
    public @ResponseBody List<Timbratura> getTimbratureFromDate() {
        return opService.getTimbratureFromDate();
    }*/

    /*
    //Ritorna dati un operaio tramite codice fiscale
    @GetMapping(path = "/op/{cf}") //http://localhost:8080/demo/op/abc
    public @ResponseBody Operaio getOperaioFromCf(@PathVariable("cf") String cf) {
        return opService.getOperaioByCF(cf);
    }*/

    /*Ritorna tutti i timbri presenti nel DB
    @GetMapping(path = "/timbro")
    public @ResponseBody List<Timbratura> getTimbrature() {
        return opService.getTimbrature();
    }*/
}