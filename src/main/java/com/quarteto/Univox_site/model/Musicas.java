package com.quarteto.univox_site.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Musicas {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer id;
    
    String titulo;
    String compositor;
    String descricao;
    String tom;
    public Musicas() {
    }
    public Musicas(Integer id, String titulo, String compositor, String descricao, String tom) {
        this.id = id;
        this.titulo = titulo;
        this.compositor = compositor;
        this.descricao = descricao;
        this.tom = tom;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getCompositor() {
        return compositor;
    }
    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTom() {
        return tom;
    }
    public void setTom(String tom) {
        this.tom = tom;
    }

    
    

}
