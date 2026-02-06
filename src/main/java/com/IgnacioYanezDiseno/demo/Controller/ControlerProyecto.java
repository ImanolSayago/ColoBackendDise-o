/*
 *  /**
 *
 *      * Copyright (c) [2025] [ImanolSayago]
 *
 *      * All rights reserved.
 *
 *
 */

package com.IgnacioYanezDiseno.demo.Controller;

import com.IgnacioYanezDiseno.demo.Dto.ProyectoEditDTO;
import com.IgnacioYanezDiseno.demo.Model.Proyecto;
import com.IgnacioYanezDiseno.demo.Respuestas.ResponseMessage;
import com.IgnacioYanezDiseno.demo.Services.ServiceProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class ControlerProyecto {

    @Autowired
    ServiceProyecto proyectoService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseMessage> crearProyecto(@RequestParam("titulo") String titulo,
                                                         @RequestParam("descripcion") String descripcion,
                                                         @RequestParam("archivos") List<MultipartFile> archivos) {
        // Crear una nueva instancia de Proyecto con los datos recibidos
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo(titulo);
        proyecto.setDescripcion(descripcion);

        // Llamar al servicio para crear el proyecto y subir las imágenes
        try {
            ResponseMessage response = proyectoService.crearProyecto(proyecto, archivos);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);  // Si el proyecto se creó con éxito, devolvemos la respuesta
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // Error en la creación del proyecto
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Error: " + e.getMessage(), false));  // Error en el proceso
        }
    }


    @GetMapping("/traer")
    public List<Proyecto> getProyectos()
    {
        return proyectoService.getProyectos();
    }

    @GetMapping("/traer/{id}")
    public Proyecto getProyectoByID(@PathVariable Long id)
    {
        return proyectoService.getProyectoByID(id);
    }

    @DeleteMapping("/borrar/{id}")
    public void deleteProyecto(@PathVariable Long id)
    {
        proyectoService.deleteProyecto(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseMessage> editProyecto(@PathVariable Long id,
                                                        @RequestParam("titulo") String titulo,
                                                        @RequestParam("descripcion") String descripcion,
                                                        @RequestParam(value = "archivos", required = false) List<MultipartFile> archivos,
                                                        @RequestParam("imagenesExistentesUrls") List<String> imagenesExistentesUrls) {
        try {
            boolean editado = proyectoService.editProyecto(id, titulo, descripcion, archivos, imagenesExistentesUrls);

            if (editado) {
                return ResponseEntity.ok(new ResponseMessage("Proyecto editado con éxito.", true));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Error al editar el proyecto.", false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Error: " + e.getMessage(), false));
        }
    }
    }

