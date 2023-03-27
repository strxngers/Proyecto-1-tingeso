package com.example.demo.services;

import com.example.demo.repositories.calidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class calidadService {
    @Autowired
    calidadRepository calidadRepository;
}
