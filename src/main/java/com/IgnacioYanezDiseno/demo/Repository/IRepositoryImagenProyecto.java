/*
 *  /**
 *
 *      * Copyright (c) [2025] [ImanolSayago]
 *
 *      * All rights reserved.
 *
 *
 */

package com.IgnacioYanezDiseno.demo.Repository;

import com.IgnacioYanezDiseno.demo.Model.ImagenProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryImagenProyecto extends JpaRepository<ImagenProyecto,Long> {
    List<ImagenProyecto> findByProyectoId(Long proyectoId);
}
