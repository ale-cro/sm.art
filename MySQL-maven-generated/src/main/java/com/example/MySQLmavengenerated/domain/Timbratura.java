package com.example.MySQLmavengenerated.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Classe Timbratura, generato tramite JPA (Database -> click dx sulla tabella -> JPA Entities from DB)
 */
@Entity
@Table(name = "timbratura", schema = "db_lavoro")
public class Timbratura {
    @Id
    @Column(name = "idtimbratura", nullable = false, length = 5)
    private String idtimbratura;

    @Column(name = "entrata", length = 5)
    private LocalDate entrata;

    //Relazione many to one con Operaio. Timbro viene eseguito da 1 Operaio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cf")
    @JsonBackReference  //per non creare recursione infinita
    private Operaio oper; //Operaio avrò più timbrature

    public String getIdtimbratura() {
        return idtimbratura;
    }

    public void setIdtimbratura(String idtimbratura) {
        this.idtimbratura = idtimbratura;
    }

    public LocalDate getEntrata() {
        return entrata;
    }

    public void LocalDate(LocalDate entrata) {
        this.entrata = entrata;
    }

    public Operaio getOper() {
        return oper;
    }

    public void setOper(Operaio oper) {
        this.oper = oper;
    }

}