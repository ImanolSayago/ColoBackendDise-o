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

import com.IgnacioYanezDiseno.demo.Model.Admin;
import com.IgnacioYanezDiseno.demo.Repository.IRepositoryAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAdmin implements IServiceAdmin {

    @Autowired
    IRepositoryAdmin repoadmin;

    @Override
    public boolean authAdmin(Admin ad) {

        boolean autenticado = false;


        // Buscar el admin por el correo electrónico
        Optional<Admin> adminOptional = repoadmin.findByNombreUsuario(ad.getNombreUsuario());
        System.out.println(ad.getNombreUsuario());

        // Verificar si se encontró el admin
        if (adminOptional.isPresent()) {
            Admin adminDb = adminOptional.get();

            // Comparar las contraseñas como cadenas de texto
            if (ad.getContrasena().equals(adminDb.getContrasena())) {
                autenticado = true; // La contraseña es correcta
            }
        }

        return autenticado;
    }

    @Override
    public boolean editAdmin(String nombreUsuario, String contrasena) {
        return false;
    }
}
