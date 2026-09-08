package com.quarteto.univox_site.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KitVoz {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer id;
    
    Integer musica_id;
    String voz;
    String arquivo;
    public KitVoz() {
    }
    public KitVoz(Integer id, Integer musica_id, String voz, String arquivo) {
        this.id = id;
        this.musica_id = musica_id;
        this.voz = voz;
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
    public String getVoz() {
        return voz;
    }
    public void setVoz(String voz) {
        this.voz = voz;
    }
    public String getArquivo() {
        return arquivo;
    }
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    

}
