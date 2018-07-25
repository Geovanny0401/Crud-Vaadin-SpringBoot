package com.gmendozag.co.Repositorio;

import com.gmendozag.co.Entity.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByApellidoStartsWithIgnoreCase(String apellido);

    List<Cliente> findAllBy(Pageable pageable);

    List<Cliente> findByApellidoLikeIgnoreCase(String nameFilter);

    // For lazy loading and filtering
    List<Cliente> findByApellidoLikeIgnoreCase(String nameFilter, Pageable pageable);

    long countByApellidoLikeIgnoreCase(String nameFilter);

    List<Cliente> findAll();

}
