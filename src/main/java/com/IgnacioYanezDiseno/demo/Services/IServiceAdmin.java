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

public interface IServiceAdmin {
    public boolean authAdmin(Admin ad);

    public boolean editAdmin(String nombreUsuario,String contrasena);
}
