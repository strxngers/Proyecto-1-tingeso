package com.example.demo.services;

import com.example.demo.repositories.acopioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class acopioService {
    @Autowired
    acopioRepository acopioRepository;
}
