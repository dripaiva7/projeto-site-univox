package com.quarteto.univox_site.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Partituras {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer id;
    Integer musica_id;
    String arquivo;
    public Partituras() {
    }
    public Partituras(Integer id, Integer musica_id, String arquivo) {
        this.id = id;
        this.musica_id = musica_id;
        this.arquivo = arquivo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMusica_id() {
        return musica_id;
    }
    public void setMusica_id(Integer musica_id) {
        this.musica_id = musica_id;
    }
    public String getArquivo() {
        return arquivo;
    }
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    

}
