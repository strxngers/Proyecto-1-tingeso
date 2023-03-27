package com.example.demo.repositories;

import com.example.demo.entities.pagoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pagoRepository extends CrudRepository<pagoEntity,Integer> {
}