package com.example.demo.services;

import com.example.demo.entities.proveedorEntity;
import com.example.demo.repositories.proveedorRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class proveedorService {
    @Autowired
    proveedorRepository proveedorRepository;

    /* Método que crea un proveedor con la información entregada
    */
    public proveedorEntity saveProveedor(Integer id_proveedor, String nombre, String categoria, String retencion){
        proveedorEntity proveedor = new proveedorEntity();
        proveedor.setId_proveedor(id_proveedor);
        proveedor.setNombre(nombre);
        proveedor.setCategoria(categoria);
        proveedor.setRetencion(retencion);
        return proveedorRepository.save(proveedor);
    }

    /* Método que pone los proveedores en un array
    */
    @Generated
    public ArrayList<proveedorEntity> listaProveedores(){
        return (ArrayList<proveedorEntity>) proveedorRepository.findAll();
    }

    /* Método que ve si el proveedor a ingresar ya se encuentra en la lista de proveedores
    */
    public boolean estaRegistrado(proveedorEntity proveedor){
        proveedorEntity newProveedor = proveedorRepository.findById(proveedor.getId_proveedor()).orElse(null);
        return newProveedor != null;
    }

    public void delete(proveedorEntity proveedor){
        if(estaRegistrado(proveedor)){
            proveedorRepository.delete(proveedor);
        }
    }
}
