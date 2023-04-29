package com.example.demo.repositories;

import com.example.demo.entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface
AcopioRepository extends JpaRepository<AcopioEntity,Integer> {
    @Override
    List<AcopioEntity> findAll();

}


