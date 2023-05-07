package com.example.demo;

import com.example.demo.repositories.AcopioRepository;
import com.example.demo.services.AcopioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AcopioTest {
    @Autowired
    AcopioService service;
    @Autowired
    AcopioRepository acopioRepository;

    @Test
    public void testQuincena() {
        // Definir los valores de prueba
        String fecha1 = "2001-09-22";
        String fecha2 = "2000-12-28";
        String fecha3 = "2021-10-16";
        String fecha4 = "2013-01-14";
        String fecha5 = "2013-02-14";
        String fecha6 = "2013-03-14";
        // Ejecutar los métodos y verificar los resultados
        assertEquals(2, service.quincena(fecha1));
        assertEquals(2, service.quincena(fecha2));
        assertEquals(2, service.quincena(fecha3));
        assertEquals(1, service.quincena(fecha4));
        assertEquals(1, service.quincena(fecha5));
        assertEquals(1, service.quincena(fecha6));
    }

}
