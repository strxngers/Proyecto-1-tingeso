package com.example.demo;

import com.example.demo.entities.AcopioEntity;
import com.example.demo.entities.PagoEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.services.PagoService;
import com.example.demo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class PagoTest {
    @Autowired
    private PagoService pago;
    @Autowired
    ProveedorService servicio;

    @Test
    void testDctoLeche() {
        double pagoTotal = 1000.0;

        // Caso variacion en rango 0-8
        double variacion1 = 5.0;
        double expectedDesc1 = 0.0;
        double actualDesc1 = pago.dctoLeche(variacion1, pagoTotal);
        assertEquals(expectedDesc1, actualDesc1, 0.001);

        // Caso variacion en rango 9-15
        double variacion2 = 10.0;
        double expectedDesc2 = 70.0;
        double actualDesc2 = pago.dctoLeche(variacion2, pagoTotal);
        assertEquals(expectedDesc2, actualDesc2, 0.001);

        // Caso variacion en rango 16-26
        double variacion3 = 20.0;
        double expectedDesc3 = 70.0;
        double actualDesc3 = pago.dctoLeche(variacion3, pagoTotal);
        assertEquals(expectedDesc3, actualDesc3, 0.001);

        // Caso variacion en rango 26-41
        double variacion4 = 35.0;
        double expectedDesc4 = 150.0;
        double actualDesc4 = pago.dctoLeche(variacion4, pagoTotal);
        assertEquals(expectedDesc4, actualDesc4, 0.001);

        // Caso variacion mayor a 41
        double variacion5 = 50.0;
        double expectedDesc5 = 300.0;
        double actualDesc5 = pago.dctoLeche(variacion5, pagoTotal);
        assertEquals(expectedDesc5, actualDesc5, 0.001);

        // Caso variacion negativa
        double variacion6 = -10.0;
        double expectedDesc6 = 0.0;
        double actualDesc6 = pago.dctoLeche(variacion6, pagoTotal);
        assertEquals(expectedDesc6, actualDesc6, 0.001);

        // Caso variacion igual a 15
        double variacion7 = 15.0;
        double expectedDesc7 = 70.0;
        double actualDesc7 = pago.dctoLeche(variacion7, pagoTotal);
        assertEquals(expectedDesc7, actualDesc7, 0.001);

        // Caso variacion igual a 26
        double variacion8 = 26.0;
        double expectedDesc8 = 150.0;
        double actualDesc8 = pago.dctoLeche(variacion8, pagoTotal);
        assertEquals(expectedDesc8, actualDesc8, 0.001);

        // Caso variacion igual a 41
        double variacion9 = 41.0;
        double expectedDesc9 = 150.0;
        double actualDesc9 = pago.dctoLeche(variacion9, pagoTotal);
        assertEquals(expectedDesc9, actualDesc9, 0.001);
    }

    @Test
    void testDctoGrasa() {
        double pagoTotal = 1000.0;

        // Caso variacion en rango 0-15
        double variacion1 = 5.0;
        double expectedDesc1 = 0.0;
        double actualDesc1 = pago.dctoGrasa(variacion1, pagoTotal);
        assertEquals(expectedDesc1, actualDesc1, 0.001);

        // Caso variacion en rango 16-25
        double variacion2 = 20.0;
        double expectedDesc2 = 120.0;
        double actualDesc2 = pago.dctoGrasa(variacion2, pagoTotal);
        assertEquals(expectedDesc2, actualDesc2, 0.001);

        // Caso variacion en rango 26-40
        double variacion3 = 35.0;
        double expectedDesc3 = 200.0;
        double actualDesc3 = pago.dctoGrasa(variacion3, pagoTotal);
        assertEquals(expectedDesc3, actualDesc3, 0.001);

        // Caso variacion mayor a 40
        double variacion4 = 50.0;
        double expectedDesc4 = 300.0;
        double actualDesc4 = pago.dctoGrasa(variacion4, pagoTotal);
        assertEquals(expectedDesc4, actualDesc4, 0.001);

        // Caso variacion negativa
        double variacion5 = -10.0;
        double expectedDesc5 = 0.0;
        double actualDesc5 = pago.dctoGrasa(variacion5, pagoTotal);
        assertEquals(expectedDesc5, actualDesc5, 0.001);

        // Caso variacion 17
        double variacion6 = 17.0;
        double expectedDesc6 = 0.12 * pagoTotal;
        double actualDesc6 = pago.dctoGrasa(variacion6, pagoTotal);
        assertEquals(expectedDesc6, actualDesc6, 0.001);

        // Caso variacion 25.5
        double variacion7 = 27.0;
        double expectedDesc7 = 0.2 * pagoTotal;
        double actualDesc7 = pago.dctoGrasa(variacion7, pagoTotal);
        assertEquals(expectedDesc7, actualDesc7, 0.001);

        // Caso variacion 42
        double variacion8 = 42.0;
        double expectedDesc8 = 0.3 * pagoTotal;
        double actualDesc8 = pago.dctoGrasa(variacion8, pagoTotal);
        assertEquals(expectedDesc8, actualDesc8, 0.001);
    }

    @Test
    void testDctoSolidos() {
        double pagoTotal = 1000.0;

        // Caso variacion en rango 0-6
        double variacion1 = 3.0;
        double expectedDesc1 = 0.0;
        double actualDesc1 = pago.dctoSolidos(variacion1, pagoTotal);
        assertEquals(expectedDesc1, actualDesc1, 0.001);

        // Caso variacion en rango 6-13
        double variacion2 = 10.0;
        double expectedDesc2 = 180.0;
        double actualDesc2 = pago.dctoSolidos(variacion2, pagoTotal);
        assertEquals(expectedDesc2, actualDesc2, 0.001);

        // Caso variacion en rango 13-36
        double variacion3 = 25.0;
        double expectedDesc3 = 270.0;
        double actualDesc3 = pago.dctoSolidos(variacion3, pagoTotal);
        assertEquals(expectedDesc3, actualDesc3, 0.001);

        // Caso variacion mayor a 36
        double variacion4 = 40.0;
        double expectedDesc4 = 450.0;
        double actualDesc4 = pago.dctoSolidos(variacion4, pagoTotal);
        assertEquals(expectedDesc4, actualDesc4, 0.001);

        // Caso variacion negativa
        double variacion5 = -10.0;
        double expectedDesc5 = 0.0;
        double actualDesc5 = pago.dctoSolidos(variacion5, pagoTotal);
        assertEquals(expectedDesc5, actualDesc5, 0.001);
    }

    @Test
    void testPorcent() {
        // Caso ambos rangos son mayores o iguales a 10
        int mrng1 = 10;
        int tarde1 = 10;
        double expectedPorcent1 = 0.2;
        double actualPorcent1 = pago.porcent(mrng1, tarde1);
        assertEquals(expectedPorcent1, actualPorcent1, 0.001);

        // Caso mañana mayor o igual a 10, tarde menor a 10
        int mrng2 = 10;
        int tarde2 = 5;
        double expectedPorcent2 = 0.12;
        double actualPorcent2 = pago.porcent(mrng2, tarde2);
        assertEquals(expectedPorcent2, actualPorcent2, 0.001);

        // Caso tarde mayor o igual a 10, mañana menor a 10
        int mrng3 = 5;
        int tarde3 = 10;
        double expectedPorcent3 = 0.08;
        double actualPorcent3 = pago.porcent(mrng3, tarde3);
        assertEquals(expectedPorcent3, actualPorcent3, 0.001);

        // Caso ambos rangos son menores a 10
        int mrng4 = 5;
        int tarde4 = 5;
        double expectedPorcent4 = 0.0;
        double actualPorcent4 = pago.porcent(mrng4, tarde4);
        assertEquals(expectedPorcent4, actualPorcent4, 0.001);
    }


    @Test
    void testPagoPorGrasa() {
        // Caso porcentaje de grasa entre 0 y 20
        int porGrasa1 = 10;
        int kls_leche1 = 5;
        int expectedPago1 = 150;
        int actualPago1 = pago.pagoPorGrasa(porGrasa1, kls_leche1);
        assertEquals(expectedPago1, actualPago1);

        // Caso porcentaje de grasa entre 20 y 45
        int porGrasa2 = 30;
        int kls_leche2 = 10;
        int expectedPago2 = 800;
        int actualPago2 = pago.pagoPorGrasa(porGrasa2, kls_leche2);
        assertEquals(expectedPago2, actualPago2);

        // Caso porcentaje de grasa mayor a 45
        int porGrasa3 = 50;
        int kls_leche3 = 8;
        int expectedPago3 = 960;
        int actualPago3 = pago.pagoPorGrasa(porGrasa3, kls_leche3);
        assertEquals(expectedPago3, actualPago3);

        // Caso porcentaje de grasa negativo
        int porGrasa4 = -10;
        int kls_leche4 = 5;
        Integer actualPago4 = pago.pagoPorGrasa(porGrasa4, kls_leche4);
        assertNull(actualPago4);
    }

    @Test
    void testPagoPorSolidos() {
        // Caso porcentaje de sólidos entre 0 y 7
        int porSolidos1 = 5;
        int kls_leche1 = 3;
        int expectedPago1 = -390;
        int actualPago1 = pago.pagoPorSolidos(porSolidos1, kls_leche1);
        assertEquals(expectedPago1, actualPago1);

        // Caso porcentaje de sólidos entre 7 y 18
        int porSolidos2 = 12;
        int kls_leche2 = 6;
        int expectedPago2 = -540;
        int actualPago2 = pago.pagoPorSolidos(porSolidos2, kls_leche2);
        assertEquals(expectedPago2, actualPago2);

        // Caso porcentaje de sólidos entre 18 y 35
        int porSolidos3 = 25;
        int kls_leche3 = 12;
        int expectedPago3 = 1140;
        int actualPago3 = pago.pagoPorSolidos(porSolidos3, kls_leche3);
        assertEquals(expectedPago3, actualPago3);

        // Caso porcentaje de sólidos mayor a 35
        int porSolidos4 = 40;
        int kls_leche4 = 8;
        int expectedPago4 = 1200;
        int actualPago4 = pago.pagoPorSolidos(porSolidos4, kls_leche4);
        assertEquals(expectedPago4, actualPago4);

        // Caso porcentaje de sólidos negativo
        int porSolidos5 = -10;
        int kls_leche5 = 5;
        Integer actualPago5 = pago.pagoPorSolidos(porSolidos5, kls_leche5);
        assertNull(actualPago5);
    }


    @Test
    void testMontoCategoria() {
        Integer klsLeche = 100;
        Integer montoA = pago.montoCategoria("A", klsLeche);
        Integer montoB = pago.montoCategoria("B", klsLeche);
        Integer montoC = pago.montoCategoria("C", klsLeche);
        Integer montoD = pago.montoCategoria("D", klsLeche);
        Integer montoE = pago.montoCategoria("E", klsLeche);

        // Comprobar que los montos son correctos para cada categoría
        assertEquals(70000, (int)montoA);
        assertEquals(55000, (int)montoB);
        assertEquals(40000, (int)montoC);
        assertEquals(25000, (int)montoD);
        assertEquals(100, (int)montoE);
    }
    @Test
    void testRetencion() {
        // Crear un proveedor con retención
        ProveedorEntity proveedorConRetencion = new ProveedorEntity();
        proveedorConRetencion.setRetencion("SI");

        // Crear un proveedor sin retención
        ProveedorEntity proveedorSinRetencion = new ProveedorEntity();
        proveedorSinRetencion.setRetencion("NO");

        // Calcular la retención para un pago total de 1000
        double pagoTotal = 1000;
        double retencionCon = pago.retencion(proveedorConRetencion, pagoTotal);
        double retencionSin = pago.retencion(proveedorSinRetencion, pagoTotal);

        // Verificar que la retención sea correcta para el proveedor con retención
        assertEquals(130.0, retencionCon, 0.01);

        // Verificar que la retención sea cero para el proveedor sin retención
        assertEquals(0.0, retencionSin, 0.01);
    }

    @Test
    void testNuevaFecha() {
        String fecha = "2023-05-01";
        String nuevaFecha = pago.nuevaFecha(fecha);
        assertEquals("2023-05-1", nuevaFecha);
    }

    @Test
    void testPromKls() {
        int klsLeche = 1200;
        int diasEnvio = 20;
        double promedio = pago.promKls(klsLeche, diasEnvio);

        assertEquals(60.0, promedio, 0.01);
    }

    @Test
    void testVarGrasaySolido() {
        Integer nuevoTotal = 30;
        Integer valor_antiguo = 50;
        Double resultadoEsperado = 20.0;
        Double resultadoReal = pago.varGrasaySolido(nuevoTotal, valor_antiguo);
        assertEquals(resultadoEsperado, resultadoReal);

        nuevoTotal = 50;
        valor_antiguo = 50;
        resultadoEsperado = 0.0;
        resultadoReal = pago.varGrasaySolido(nuevoTotal, valor_antiguo);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void testVariacion() {
        double resultadoEsperado = 0.0;
        double resultadoObtenido = pago.variacion(150.0, 150.0);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }


    @Test
    void testPagoTotal() {
        PagoEntity pagos = new PagoEntity();
        double pagoLeche = 5000.0;
        double dctoVarST = 0.0;
        double dctoVarGrasa = 0.0;
        double dctoVarLeche = 0.0;
        pagos.setDctoVarST(dctoVarST);
        pagos.setDctoVarGrasa(dctoVarGrasa);
        pagos.setDctoVarLeche(dctoVarLeche);

        // Caso 1: Todos los descuentos son 0
        pago.pagoTotal(pagos, pagoLeche);
        double expectedPagoTotal = 5000.0;
        assertEquals(expectedPagoTotal, pagos.getPagoTotal(), 0.0);

        // Caso 2: Descuento de variación de sólidos totales
        dctoVarST = 250.0;
        pagos.setDctoVarST(dctoVarST);
        pago.pagoTotal(pagos, pagoLeche);
        expectedPagoTotal = 4750.0;
        assertEquals(expectedPagoTotal, pagos.getPagoTotal(), 0.0);

        // Caso 3: Descuento de variación de grasa
        dctoVarST = 0.0;
        dctoVarGrasa = 600.0;
        pagos.setDctoVarST(dctoVarST);
        pagos.setDctoVarGrasa(dctoVarGrasa);
        pago.pagoTotal(pagos, pagoLeche);
        expectedPagoTotal = 4400.0;
        assertEquals(expectedPagoTotal, pagos.getPagoTotal(), 0.0);

        // Caso 4: Descuento de variación de leche
        dctoVarGrasa = 0.0;
        dctoVarLeche = 1000.0;
        pagos.setDctoVarGrasa(dctoVarGrasa);
        pagos.setDctoVarLeche(dctoVarLeche);
        pago.pagoTotal(pagos, pagoLeche);
        expectedPagoTotal = 4000.0;
        assertEquals(expectedPagoTotal, pagos.getPagoTotal(), 0.0);

        // Caso 5: Todos los descuentos están presentes
        dctoVarST = 150.0;
        dctoVarGrasa = 300.0;
        dctoVarLeche = 500.0;
        pagos.setDctoVarST(dctoVarST);
        pagos.setDctoVarGrasa(dctoVarGrasa);
        pagos.setDctoVarLeche(dctoVarLeche);
        pago.pagoTotal(pagos, pagoLeche);
        expectedPagoTotal = 4050.0;
        assertEquals(expectedPagoTotal, pagos.getPagoTotal(), 0.0);

        PagoEntity newPago = new PagoEntity();
        newPago.setDctoVarST(0.0);
        newPago.setDctoVarGrasa(0.0);
        newPago.setDctoVarLeche(0.0);
        double pagoLeche4 = 0.0;
        pago.pagoTotal(newPago,pagoLeche4);
        assertEquals(0.0,newPago.getPagoTotal());

    }

    @Test
    void testDescuentos() {
        // Crear un objeto PagoEntity
        PagoEntity pagos = new PagoEntity();
        pagos.setVariacionLeche(10.0);
        pagos.setVarGrasa(20.0);
        pagos.setVarST(30.0);

        // Llamar al método descuentos con un pagoTotal de 100.0
        double pagoTotal = 100.0;
        pago.descuentos(pagos, pagoTotal);

        // Verificar que los descuentos fueron calculados correctamente
        assertEquals(7.0, pagos.getDctoVarLeche());
        assertEquals(12.0, pagos.getDctoVarGrasa());
        assertEquals(27.0, pagos.getDctoVarST());

        // Crear otro objeto PagoEntity con valores diferentes
        PagoEntity otroPago = new PagoEntity();
        otroPago.setVariacionLeche(5.0);
        otroPago.setVarGrasa(10.0);
        otroPago.setVarST(20.0);

        // Llamar al método descuentos con un pagoTotal de 50.0
        pagoTotal = 50.0;
        pago.descuentos(otroPago, pagoTotal);

        // Verificar que los descuentos fueron calculados correctamente
        assertEquals(0.0, otroPago.getDctoVarLeche());
        assertEquals(0.0, otroPago.getDctoVarGrasa());
        assertEquals(14.0, otroPago.getDctoVarST());
    }

    @Test
    void infoProveedorFechaTest() {
        ProveedorEntity proveedor = new ProveedorEntity(12345,"Vaca Lola");
        AcopioEntity acopio = new AcopioEntity("2021/10/16","M");
        ArrayList<AcopioEntity> lista = new ArrayList<AcopioEntity>();
        lista.add(acopio);
        PagoEntity pagoI = new PagoEntity();
        pago.infoProveedorFecha(proveedor,lista,pagoI);

        assertEquals("2021-10-2",pagoI.getFecha());
        assertEquals(12345,pagoI.getCodigo());
        assertEquals(proveedor,pagoI.getProveedor());
        assertEquals("Vaca Lola", pagoI.getNombreProveedor());
    }

    @Test
    void mrngTTest(){
        AcopioEntity acopio = new AcopioEntity("2021/10/16","M");
        AcopioEntity acopio2 = new AcopioEntity("2022/10/16","T");
        AcopioEntity acopio3 = new AcopioEntity("2023/10/16","M");
        ArrayList<AcopioEntity> lista = new ArrayList<AcopioEntity>();
        lista.add(acopio);
        lista.add(acopio2);
        lista.add(acopio3);
        List<Integer> i = pago.mrngT(lista);
        assertEquals(2,i.get(0));
        assertEquals(1,i.get(1));
    }

}