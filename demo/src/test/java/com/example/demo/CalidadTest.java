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
public class CalidadTest {
    @Autowired
    CalidadService calidadService;
    @Autowired
    CalidadRepository calidadRepository;

    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    ProveedorService proveedorService;

    @Test
    void testExiste() {
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setNombre("Proveedor 1");
        proveedor.setId_proveedor(12345);
        proveedorRepository.save(proveedor);

        CalidadEntity calidad = new CalidadEntity();
        calidad.setPor_grasa(20);
        calidad.setPor_solidos(15);
        calidad.setProveedor(proveedor);
        calidadRepository.save(calidad);

        assertTrue(calidadService.existe(calidad));

        CalidadEntity calidad2 = new CalidadEntity();
        calidad2.setPor_grasa(25);
        calidad2.setPor_solidos(20);
        calidad2.setProveedor(proveedor);
        calidadRepository.save(calidad2);

        assertTrue(calidadService.existe(calidad));
        assertTrue(calidadService.existe(calidad2));

        CalidadEntity calidad3 = new CalidadEntity();
        ProveedorEntity proveedor2 = new ProveedorEntity();
        proveedor2.setNombre("Proveedor 2");
        proveedor2.setId_proveedor(54321);
        proveedorRepository.save(proveedor2);
        calidad3.setProveedor(proveedor2);

        assertFalse(calidadService.existe(calidad3));

        calidadService.delete(calidad);
        calidadService.delete(calidad2);
        proveedorService.delete(proveedor);
        proveedorService.delete(proveedor2);
    }

    @Test
    void deleteTest() {
        // Crear un objeto de proveedor
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setNombre("Proveedor 1");
        proveedor.setId_proveedor(12345);
        proveedorRepository.save(proveedor);

        // Crear un objeto de calidad que exista en el repositorio
        CalidadEntity calidadExistente = new CalidadEntity();
        calidadExistente.setPor_grasa(20);
        calidadExistente.setPor_solidos(15);
        calidadExistente.setProveedor(proveedor);
        calidadRepository.save(calidadExistente);

        // Verificar que la calidad exista en el repositorio
        assertTrue(calidadService.existe(calidadExistente));

        // Eliminar la calidad existente del repositorio
        calidadRepository.delete(calidadExistente);

        // Verificar que la calidad ya no exista en el repositorio
        assertFalse(calidadService.existe(calidadExistente));

        // Crear un objeto de calidad que NO exista en el repositorio
        CalidadEntity calidadInexistente = new CalidadEntity();
        calidadInexistente.setPor_grasa(30);
        calidadInexistente.setPor_solidos(25);
        calidadInexistente.setProveedor(proveedor);

        // Verificar que la calidad NO exista en el repositorio
        assertFalse(calidadService.existe(calidadInexistente));

        // Eliminar la calidad inexistente del repositorio
        calidadRepository.delete(calidadInexistente);

        // Verificar que la calidad inexistente sigue sin existir en el repositorio
        assertFalse(calidadService.existe(calidadInexistente));
    }

}
