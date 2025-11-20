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

import com.IgnacioYanezDiseno.demo.Model.ImagenProyecto;
import com.IgnacioYanezDiseno.demo.Model.Proyecto;
import com.IgnacioYanezDiseno.demo.Repository.IRepositoryImagenProyecto;
import com.IgnacioYanezDiseno.demo.Repository.IRepositoryProyecto;
import com.IgnacioYanezDiseno.demo.Respuestas.ResponseMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProyecto implements IServiceProyecto {

    @Autowired
    CloudinaryService cloudserv;

    @Autowired
    IRepositoryProyecto repoproyecto;

    @Autowired
    IRepositoryImagenProyecto repoImagenProyecto;


    @Override
    public ResponseMessage crearProyecto(Proyecto proyecto, List<MultipartFile> archivos) {
        // Subir la imagen principal (primer archivo de la lista)
        String imagenPrincipalUrl = null;
        try {
            imagenPrincipalUrl = cloudserv.subirImagen(archivos.get(0));  // Usando el primer archivo como imagen principal
        } catch (IOException e) {
            e.printStackTrace();  // Para poder loguear el error
            return new ResponseMessage("Hubo un error al subir la imagen principal", false);  // Error al subir imagen principal
        }
        proyecto.setImagenPrincipal(imagenPrincipalUrl); // Asignamos la URL de la imagen principal al proyecto

        // Guardar el proyecto con la URL de la imagen principal
        Proyecto proyectoGuardado = repoproyecto.save(proyecto);

        // Subir imágenes adicionales (si existen) y asociarlas al proyecto
        for (int i = 1; i < archivos.size(); i++) {
            String imagenUrl = null;
            try {
                imagenUrl = cloudserv.subirImagen(archivos.get(i));  // Subimos cada archivo adicional
            } catch (IOException e) {
                e.printStackTrace();  // Para poder loguear el error
                return new ResponseMessage("Hubo un error al subir una imagen adicional", false);  // Error al subir imagen adicional
            }

            // Crear una nueva entidad de ImagenProyecto y asociarla al proyecto
            ImagenProyecto imagenProyecto = new ImagenProyecto();
            imagenProyecto.setUrl(imagenUrl);  // Asignamos la URL de la imagen
            imagenProyecto.setProyecto(proyectoGuardado);  // Asociamos la imagen al proyecto

            // Guardamos la imagen adicional en la base de datos
            repoImagenProyecto.save(imagenProyecto);
        }

        return new ResponseMessage("Proyecto creado con éxito", true);  // Respuesta exitosa
    }



    @Override
    public void borrarProyecto(Long id) {

    }

    @Override
    public List<Proyecto> getProyectos() {
        return repoproyecto.findAll();
    }

    @Override
    public Proyecto getProyectoByID(Long id) {
        return repoproyecto.findById(id).orElse(null);
    }

    @Override
    public boolean deleteProyecto(Long id) {

        if(id!=null)
        {
          Proyecto pro =   repoproyecto.findById(id).orElse(null);

          if(pro!=null)
          {
              repoproyecto.delete(pro);
              return true;
          }

        }
        else
        {
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean editProyecto(Long id, String titulo, String descripcion, List<MultipartFile> archivos, List<String> imagenesExistentesUrls) {
        try {
            Optional<Proyecto> proyectoOptional = repoproyecto.findById(id);
            if (!proyectoOptional.isPresent()) {
                return false;
            }

            Proyecto proyecto = proyectoOptional.get();
            proyecto.setTitulo(titulo);
            proyecto.setDescripcion(descripcion);
            repoproyecto.save(proyecto);

            // Eliminar imágenes existentes que no están en la lista actualizada

            List<ImagenProyecto> imagenesExistentes = repoImagenProyecto.findByProyectoId(id);
            for (ImagenProyecto imagen : imagenesExistentes) {
                if (!imagenesExistentesUrls.contains(imagen.getUrl())) {
                    cloudserv.eliminarImagen(imagen.getUrl()); // Elimina la imagen de Cloudinary
                    repoImagenProyecto.delete(imagen); // Elimina la imagen de la base de datos

                }
            }

            // Subir nuevas imágenes y guardarlas en la base de datos
            if (archivos != null && !archivos.isEmpty()) {
                for (MultipartFile archivo : archivos) {
                    String url = cloudserv.subirImagen(archivo);
                    ImagenProyecto nuevaImagen = new ImagenProyecto();
                    nuevaImagen.setUrl(url);
                    nuevaImagen.setProyecto(proyecto);
                    repoImagenProyecto.save(nuevaImagen);

                }
            }
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
