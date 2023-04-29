package com.example.demo.repositories;

import com.example.demo.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Integer> {

    @Query("SELECT p FROM ProveedorEntity p WHERE p.id_proveedor = :id_proveedor")
    ProveedorEntity findByIdProveedor(@Param("id_proveedor") Integer id_proveedor);
}
