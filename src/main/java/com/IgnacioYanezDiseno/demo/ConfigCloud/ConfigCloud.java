/*
 *  /**
 *
 *      * Copyright (c) [2025] [ImanolSayago]
 *
 *      * All rights reserved.
 *
 *
 */

package com.IgnacioYanezDiseno.demo.ConfigCloud;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCloud {

    @Bean
    public Cloudinary cloudinary() {

        Dotenv dotenv = Dotenv.load();
        return new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    }
}
