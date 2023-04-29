package com.example.demo;

import com.example.demo.entities.ProveedorEntity;
import com.example.demo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class proveedorTest {
    @Autowired
    ProveedorService servicio;

    @Test
    void estaRegistradoTest(){
        ProveedorEntity proveedor = servicio.saveProveedor(9999,"nombre","A","No");
        boolean resultado = servicio.estaRegistrado(proveedor);
        assertTrue(resultado);
        servicio.delete(proveedor);
    }
}
