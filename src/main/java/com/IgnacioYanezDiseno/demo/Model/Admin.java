package com.IgnacioYanezDiseno.demo.Model;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    private String contrasena;


    public Admin() {
    }

    public Admin(Long id, String nombreUsuario, String contrasena) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}


