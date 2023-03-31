package com.example.demo;

import com.example.demo.entities.proveedorEntity;
import com.example.demo.services.proveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class proveedorTest {
    @Autowired
    proveedorService servicio;

    @Test
    void estaRegistradoTest(){
        proveedorEntity proveedor = servicio.saveProveedor(9999,"nombre","A","No");
        boolean resultado = servicio.estaRegistrado(proveedor);

        assertTrue(resultado);

        servicio.delete(proveedor);
    }
}
