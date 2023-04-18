package com.example.demo.controllers;

import com.example.demo.services.acopioService;
import com.example.demo.services.calidadService;
import com.example.demo.services.pagoService;
import com.example.demo.services.proveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class pagoController {
    @Autowired
    private acopioService acopioData;
    private proveedorService proveedorData;
    private calidadService calidadData;
    private pagoService pagoData;

    @GetMapping("/planillaPagos")
    public String pagos(){
        return "planillaPagos";
    }

}
