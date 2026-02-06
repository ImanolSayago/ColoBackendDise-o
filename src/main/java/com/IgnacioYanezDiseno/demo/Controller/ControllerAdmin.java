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

import com.IgnacioYanezDiseno.demo.Model.Admin;
import com.IgnacioYanezDiseno.demo.Services.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class ControllerAdmin {

    @Autowired
    ServiceAdmin servAdmin;

    @PostMapping("/login")
    public ResponseEntity<Boolean> authAdmin(@RequestBody Admin ad)
    {
        // Llamar al servicio para verificar las credenciales
        boolean autenticado = servAdmin.authAdmin(ad);

        // Devolvemos un booleano
        if (autenticado) {

            return ResponseEntity.ok(true);  // Autenticación exitosa
        } else {

            return ResponseEntity.status(401).body(false);  // Autenticación fallida
        }
    }
}
