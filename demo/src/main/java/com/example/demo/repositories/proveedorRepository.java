package com.example.demo.repositories;

import com.example.demo.entities.proveedorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface proveedorRepository extends CrudRepository<proveedorEntity,Integer> {
}
