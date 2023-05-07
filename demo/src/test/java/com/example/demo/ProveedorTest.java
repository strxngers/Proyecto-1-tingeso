package com.example.demo;

import com.example.demo.entities.CalidadEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProveedorTest {
    @Autowired
    ProveedorService servicio;

    @Test
    void estaRegistradoTest(){
        ProveedorEntity proveedor = servicio.saveProveedor(99999,"nombre","A","No");
        boolean resultado = servicio.estaRegistrado(proveedor);
        assertTrue(resultado);
        servicio.delete(proveedor);
        // Crear proveedor no registrado
        ProveedorEntity proveedor1 = new ProveedorEntity();
        proveedor1.setId_proveedor(1);

        // Probar proveedor no registrado
        boolean result1 = servicio.estaRegistrado(proveedor1);
        assertFalse(result1);
    }


}
