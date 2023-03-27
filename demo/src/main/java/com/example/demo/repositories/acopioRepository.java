package com.example.demo.repositories;

import com.example.demo.entities.acopioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface acopioRepository extends CrudRepository<acopioEntity,Integer>{

}
