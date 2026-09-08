package com.quarteto.univox_site.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fotos {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer id;
    
    Integer album_id;
    String legenda;
    String foto_url;

    public Fotos() {
    }

    public Fotos(Integer id, Integer album_id, String legenda, String foto_url) {
        this.id = id;
        this.album_id = album_id;
        this.legenda = legenda;
        this.foto_url = foto_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    
    

}
