package com.example.MySQLmavengenerated.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Classe Utente, generato tramite JPA (Database -> click dx sulla tabella -> JPA Entities from DB)
 */
@Entity
@Table(name = "utente", schema = "db_lavoro")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutente", nullable = false)
    private Integer idutente;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    //relazione many to one con Ruolo. Utente può avere 1 Ruolo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idruolo")
    @JsonBackReference
    private Ruolo ruolo;

    @Column(name = "email", length = 45)
    private String email;

    public Integer getIdutente() {
        return idutente;
    }

    public void setIdutente(Integer idutente) {
        this.idutente = idutente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}