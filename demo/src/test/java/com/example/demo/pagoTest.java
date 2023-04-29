package com.example.demo;

import com.example.demo.services.PagoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class pagoTest {
    @Autowired
    PagoService service;

    @Test
    public void testPorcent() {
        // Probamos algunos casos de ejemplo
        double resultado1 = service.porcent(15, 12);
        assertEquals(0.2, resultado1, 0.001);  // Debe retornar el porcentaje correcto (0.2)

        double resultado2 = service.porcent(12, 8);
        assertEquals(0.12, resultado2, 0.001);  // Debe retornar el porcentaje correcto (0.12)

        double resultado3 = service.porcent(6, 10);
        assertEquals(0.08, resultado3, 0.001);  // Debe retornar el porcentaje correcto (0.08)

        double resultado4 = service.porcent(8, 8);
        assertEquals(0.0, resultado4, 0.001);  // Debe retornar el porcentaje correcto (0.0)
    }

    @Test
    public void testMontoCategoria() {
        assertEquals(3500, service.montoCategoria("A", 5));
        assertEquals(2750, service.montoCategoria("B", 5));
        assertEquals(2000, service.montoCategoria("C", 5));
        assertEquals(1250, service.montoCategoria("D", 5));
        assertEquals(10, service.montoCategoria("E", 10));
    }

    @Test
    public void testPagoPorGrasa() {
        // Caso en el que porGrasa es menor o igual a 0
        assertEquals(null, service.pagoPorGrasa(0, 100));
        // Caso en el que porGrasa está en el rango de 0 a 20
        assertEquals(3000, service.pagoPorGrasa(10, 100));
        // Caso en el que porGrasa está en el rango de 20 a 45
        assertEquals(8000, service.pagoPorGrasa(30, 100));
        // Caso en el que porGrasa es mayor a 45
        assertEquals(12000, service.pagoPorGrasa(50, 100));
    }

    @Test
    public void testPagoPorSolidos() {
        // Caso en el que porSolidos es menor o igual a 0
        assertEquals(null, service.pagoPorSolidos(0, 100));
        // Caso en el que porSolidos está en el rango de 0 a 7
        assertEquals(-13000, service.pagoPorSolidos(5, 100));
        // Caso en el que porSolidos está en el rango de 7 a 18
        assertEquals(-9000, service.pagoPorSolidos(12, 100));
        // Caso en el que porSolidos está en el rango de 18 a 35
        assertEquals(9500, service.pagoPorSolidos(25, 100));
        // Caso en el que porSolidos es mayor a 35
        assertEquals(15000, service.pagoPorSolidos(40, 100));
    }

    @Test
    public void testVariacion() {
        // Caso en el que la variación es positiva
        assertEquals(50.0, service.variacion(100, 150), 0.001);
        // Caso en el que la variación es negativa
        assertEquals(-33.333, service.variacion(150, 100), 0.001);
    }

    @Test
    public void testVarNegLeche() {
        // Caso en el que la variación es 0
        assertEquals(0, service.varNegLeche(0.0));
        // Caso en el que la variación está en el rango de 0 a 8
        assertEquals(0, service.varNegLeche(5.0));
        // Caso en el que la variación está en el rango de 8 a 26
        assertEquals(7, service.varNegLeche(15.0));
        // Caso en el que la variación está en el rango de 26 a 46
        assertEquals(15, service.varNegLeche(35.0));
        // Caso en el que la variación es mayor a 46
        assertEquals(30, service.varNegLeche(50.0));
    }

    @Test
    public void testVarNegGrasa() {
        // Caso en el que la variación es 0
        assertEquals(0, service.varNegGrasa(0.0));
        // Caso en el que la variación está en el rango de 0 a 15
        assertEquals(0, service.varNegGrasa(10.0));
        // Caso en el que la variación está en el rango de 15 a 26
        assertEquals(12, service.varNegGrasa(20.0));
        // Caso en el que la vari
    }
}