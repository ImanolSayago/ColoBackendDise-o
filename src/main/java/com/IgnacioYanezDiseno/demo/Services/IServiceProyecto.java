/*
 *  /**
 *
 *      * Copyright (c) [2025] [ImanolSayago]
 *
 *      * All rights reserved.
 *
 *
 */

package com.IgnacioYanezDiseno.demo.Services;

import com.IgnacioYanezDiseno.demo.Model.Proyecto;
import com.IgnacioYanezDiseno.demo.Respuestas.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IServiceProyecto {

    public ResponseMessage crearProyecto(Proyecto proyecto, List<MultipartFile> archivos);

    public void borrarProyecto(Long id);

    public List<Proyecto>getProyectos();

    public Proyecto getProyectoByID(Long id);

    public boolean deleteProyecto(Long id);

    public boolean editProyecto(Long id, String titulo, String descripcion, List<MultipartFile> archivos, List<String> imagenesExistentesUrls);
}
