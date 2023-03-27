package com.example.demo.repositories;

import com.example.demo.entities.calidadEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface calidadRepository extends CrudRepository<calidadEntity,Integer> {
}