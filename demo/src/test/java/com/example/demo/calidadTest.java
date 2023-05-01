package com.example.demo;

import com.example.demo.entities.CalidadEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.repositories.CalidadRepository;
import com.example.demo.repositories.ProveedorRepository;
import com.example.demo.services.CalidadService;
import com.example.demo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class calidadTest {
    @Autowired
    CalidadService calidadService;
    @Autowired
    CalidadRepository calidadRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProveedorService proveedorService;

    @Test
    public void testExiste() {
        // Creamos un proveedor
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setNombre("Proveedor 1");
        proveedor.setId_proveedor(12345);
        proveedorRepository.save(proveedor);

        // Creamos una calidad
        CalidadEntity calidad = new CalidadEntity();
        calidad.setPor_grasa(20);
        calidad.setPor_solidos(15);
        calidad.setProveedor(proveedor);
        calidadRepository.save(calidad);

        // Comprobamos que la calidad existe
        assertTrue(calidadService.existe(calidad));

        // Creamos una nueva calidad
        CalidadEntity calidad2 = new CalidadEntity();
        calidad2.setPor_grasa(25);
        calidad2.setPor_solidos(20);
        calidad2.setProveedor(proveedor);
        calidadRepository.save(calidad2);

        // Comprobamos que la calidad anterior no existe y la nueva sí
        assertTrue(calidadService.existe(calidad));
        assertTrue(calidadService.existe(calidad2));
        calidadService.delete(calidad);
        calidadService.delete(calidad2);
        proveedorService.delete(proveedor);
    }
}
