package com.example.demo.repositories;

import com.example.demo.entities.CalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalidadRepository extends JpaRepository<CalidadEntity,Integer> {

    @Query("SELECT c FROM ProveedorEntity c WHERE c.id_proveedor = :id_proveedor")
    CalidadEntity findByIdProveedor(@Param("id_proveedor") Integer id_proveedor);

}