package com.example.MySQLmavengenerated.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Classe Operaio, generato tramite JPA (Database -> click dx sulla tabella -> JPA Entities from DB)
 */
@Entity
@Table(name = "operaio", schema = "db_lavoro")
public class Operaio {

    @Id
    @Column(name = "cf", nullable = false, length = 5)
    private String cf;

    @Column(name = "Nome", length = 5)
    private String nome;

    //Oparaio ha relazione 1-n con Timbratura. Operaio esegue n Timbratura
    @OneToMany(mappedBy = "oper")
    @JsonManagedReference //per non creare recursione infinita,
    private Set<Timbratura> timbraturas = new LinkedHashSet<>();

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Timbratura> getTimbraturas() {
        return timbraturas;
    }

    public void setTimbraturas(Set<Timbratura> timbraturas) {
        this.timbraturas = timbraturas;
    }

    public void test(){

    }

}