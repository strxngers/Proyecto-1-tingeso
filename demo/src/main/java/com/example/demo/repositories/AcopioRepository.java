package com.example.demo.repositories;

import com.example.demo.entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
AcopioRepository extends JpaRepository<AcopioEntity,Integer> {


}


