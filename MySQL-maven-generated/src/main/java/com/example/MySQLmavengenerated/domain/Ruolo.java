package com.example.MySQLmavengenerated.domain;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe Ruolo, generato tramite JPA (Database -> click dx sulla tabella -> JPA Entities from DB)
 */
@Entity
@Table(name = "ruolo", schema = "db_lavoro")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idruolo", nullable = false)
    private Integer idruolo;

    @Column(name = "nome", length = 45)
    private String nome;


    //relazione one to many con Ruolo. Ruolo ha n Utenti
    @OneToMany(mappedBy = "ruolo")
    @JsonManagedReference //per non creare recursione infinita,
    private Set<Utente> utentes = new LinkedHashSet<>();

    public Integer getIdruolo() {
        return idruolo;
    }

    public void setIdruolo(Integer idruolo) {
        this.idruolo = idruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Utente> getUsers() {
        return utentes;
    }

    public void setUsers(Set<Utente> utentes) {
        this.utentes = utentes;
    }

}