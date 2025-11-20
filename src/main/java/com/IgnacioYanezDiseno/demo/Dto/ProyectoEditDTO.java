/*
 *  /**
 *
 *      * Copyright (c) [2025] [ImanolSayago]
 *
 *      * All rights reserved.
 *
 *
 */

package com.IgnacioYanezDiseno.demo.Dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProyectoEditDTO {
    private String titulo;
    private String descripcion;
    private List<MultipartFile> archivos;
    private List<String> imagenesExistentesUrls;

    // Getters y setters
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

    public List<MultipartFile> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<MultipartFile> archivos) {
        this.archivos = archivos;
    }

    public List<String> getImagenesExistentesUrls() {
        return imagenesExistentesUrls;
    }

    public void setImagenesExistentesUrls(List<String> imagenesExistentesUrls) {
        this.imagenesExistentesUrls = imagenesExistentesUrls;
    }
}
