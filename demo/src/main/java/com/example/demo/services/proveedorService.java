package com.example.demo.services;

import com.example.demo.repositories.proveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class proveedorService {
    @Autowired
    proveedorRepository proveedorRepository;
}
