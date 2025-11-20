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

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String subirImagen(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString(); // Devuelve la URL de la imagen subida
    }

    public void eliminarImagen(String imageUrl) throws IOException {
        String publicId = extractPublicId(imageUrl);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    private String extractPublicId(String imageUrl) {
        // Ejemplo: https://res.cloudinary.com/tu-cloud-name/image/upload/v1234567890/publicId.jpg
        String[] parts = imageUrl.split("/");
        String publicIdWithExtension = parts[parts.length - 1]; // Obtiene publicId.jpg
        return publicIdWithExtension.substring(0, publicIdWithExtension.lastIndexOf('.')); // Obtiene publicId
    }
}
