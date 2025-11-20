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
import com.IgnacioYanezDiseno.demo.Repository.IRepositoryImagenProyecto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceImagenProyecto implements IServiceImagenProyecto{
    @Autowired
    IRepositoryImagenProyecto repoimg;

    @Override
    public List<ImagenProyecto> getImagenes() {
        return repoimg.findAll();
    }
}
