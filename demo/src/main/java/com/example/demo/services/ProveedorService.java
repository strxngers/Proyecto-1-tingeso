package com.example.demo.services;

import com.example.demo.entities.ProveedorEntity;
import com.example.demo.repositories.ProveedorRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    // Método que crea un proveedor con la información entregada
    public ProveedorEntity saveProveedor(Integer id_proveedor, String nombre, String categoria, String retencion) {
        ProveedorEntity proveedor = new ProveedorEntity(id_proveedor, nombre, categoria, retencion);
        if (!estaRegistrado(proveedor)) {
            return proveedorRepository.save(proveedor);
        }else
            return null;
    }

    // Método que pone los proveedores en un array
    @Generated
    public ArrayList<ProveedorEntity> listaProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
    }

    // Método que ve si el proveedor a ingresar ya se encuentra en la lista de proveedores
    public boolean estaRegistrado(ProveedorEntity proveedor){
        ProveedorEntity newProveedor = proveedorRepository.findById(proveedor.getId_proveedor()).orElse(null);
        return newProveedor != null;
    }

    public void delete(ProveedorEntity proveedor){
        if(estaRegistrado(proveedor)){
            proveedorRepository.delete(proveedor);
        }
    }

}