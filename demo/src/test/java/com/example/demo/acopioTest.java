package com.example.demo;

import com.example.demo.services.AcopioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class acopioTest {
    @Autowired
    AcopioService service;

    @Test
    public void testQuincena() {
        // Definir los valores de prueba
        String fecha1 = "2001-09-22";
        String fecha2 = "2000-12-28";
        String fecha3 = "2021-10-16";
        String fecha4 = "2013-01-14";
        // Ejecutar los métodos y verificar los resultados
        assertEquals(2, service.quincena(fecha1));
        assertEquals(2, service.quincena(fecha2));
        assertEquals(2, service.quincena(fecha3));
        assertEquals(1, service.quincena(fecha4));
    }


}
