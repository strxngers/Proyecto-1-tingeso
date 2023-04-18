package com.example.demo.repositories;

import com.example.demo.entities.CalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalidadRepository extends JpaRepository<CalidadEntity,Integer> {
}