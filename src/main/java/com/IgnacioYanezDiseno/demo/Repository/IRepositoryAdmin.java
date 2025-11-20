package com.IgnacioYanezDiseno.demo.Repository;

import com.IgnacioYanezDiseno.demo.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryAdmin extends JpaRepository<Admin, Long> {
    Optional<Admin> findByNombreUsuario(String nombreUsuario);
}
