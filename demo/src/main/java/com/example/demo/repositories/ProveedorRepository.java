package com.example.demo.repositories;

import com.example.demo.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Integer> {

}
