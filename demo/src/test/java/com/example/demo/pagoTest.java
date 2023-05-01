package com.example.demo;

import com.example.demo.services.PagoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class pagoTest {
    @Autowired
    private PagoService pago;
    @Test
    public void testDctoLeche() {
        double variacion = 10.0;
        double pagoTotal = 100.0;
        double descuentoEsperado = 7.0 * pagoTotal;
        double descuentoObtenido = pago.dctoLeche(variacion, pagoTotal);
        assertEquals(descuentoEsperado, descuentoObtenido);
    }

    @Test
    public void testDctoGrasa() {
        double variacion = 20.0;
        double pagoTotal = 100.0;
        double descuentoEsperado = 12.0 * pagoTotal;
        double descuentoObtenido = pago.dctoGrasa(variacion, pagoTotal);
        assertEquals(descuentoEsperado, descuentoObtenido);
    }

    @Test
    public void testDctoSolidos() {
        double variacion = 6.0;
        double pagoTotal = 100.0;
        double descuentoEsperado = 0.0;
        double descuentoObtenido = pago.dctoSolidos(variacion, pagoTotal);
        assertEquals(descuentoEsperado, descuentoObtenido);
    }

    @Test
    public void testPorcent() {
        int mrng = 10;
        int tarde = 10;
        double porcentajeEsperado = 0.2;
        double porcentajeObtenido = pago.porcent(mrng, tarde);
        assertEquals(porcentajeEsperado, porcentajeObtenido);
    }

    @Test
    public void testPagoPorGrasa() {
        int porGrasa = 30;
        int kls_leche = 10;
        int pagoEsperado = kls_leche * 80;
        Integer pagoObtenido = pago.pagoPorGrasa(porGrasa, kls_leche);
        assertEquals(pagoEsperado, pagoObtenido);
    }

    @Test
    public void testPagoPorSolidos() {
        int porSolidos = 20;
        int kls_leche = 10;
        int pagoEsperado = kls_leche * 95;
        Integer pagoObtenido = pago.pagoPorSolidos(porSolidos, kls_leche);
        assertEquals(pagoEsperado, pagoObtenido);
    }


}