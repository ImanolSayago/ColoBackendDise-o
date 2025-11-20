/*
 *  /**
 *
 *      * Copyright (c) [2025] [ImanolSayago]
 *
 *      * All rights reserved.
 *
 *
 */

package com.IgnacioYanezDiseno.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String imagenPrincipal;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenProyecto> listaImg;


    public Proyecto() {
    }

    public Proyecto(Long id, String titulo, String descripcion, List<ImagenProyecto> listaImg) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.listaImg = listaImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public List<ImagenProyecto> getListaImg() {
        return listaImg;
    }

    public void setListaImg(List<ImagenProyecto> listaImg) {
        this.listaImg = listaImg;
    }
}
