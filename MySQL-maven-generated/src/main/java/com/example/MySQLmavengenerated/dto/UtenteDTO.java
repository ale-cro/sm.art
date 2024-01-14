package com.example.MySQLmavengenerated.dto;
/**
 * Classe DTO per Utente
 */
public class UtenteDTO {
    private int idutente;
    private String nome;
    private String password;
    private String email;
    private String ruolo_utente;

    public UtenteDTO(int idutente, String nome, String password, String email, String ruolo_utente) {
        this.idutente = idutente;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.ruolo_utente = ruolo_utente;
    }

    public int getIdutente() {
        return idutente;
    }

    public void setIdutente(int idutente) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRuolo_utente() {
        return ruolo_utente;
    }

    public void setRuolo_utente(String ruolo_utente) {
        this.ruolo_utente = ruolo_utente;
    }

}
