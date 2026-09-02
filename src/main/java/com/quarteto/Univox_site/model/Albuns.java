package com.quarteto.univox_site.model;

import java.sql.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Albuns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String titulo;
    String descricao;
    Date data;
    String capa_url;

    public Albuns() {
    }

    public Albuns(Integer id, String titulo, String descricao, Date data, String capa_url) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.capa_url = capa_url;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCapa_url() {
        return capa_url;
    }

    public void setCapa_url(String capa_url) {
        this.capa_url = capa_url;
    }

    
    



}
