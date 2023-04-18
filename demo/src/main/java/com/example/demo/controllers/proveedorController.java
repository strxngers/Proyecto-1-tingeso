package com.example.demo.controllers;

import com.example.demo.entities.ProveedorEntity;
import com.example.demo.services.proveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping
public class proveedorController {
    @Autowired
    private proveedorService proveedorService;

    @GetMapping("/proveedores")
    public String listado(Model model){
        ArrayList<ProveedorEntity> proveedores = proveedorService.listaProveedores();
        model.addAttribute("proveedores",proveedores);
        return "listaDeProveedores";
    }

    @GetMapping("/registrarProveedor")
    public String proveedor(){
        return "regitrarProveedor";
    }

    @PostMapping("/registrarProveedor")
    public String nuevoProveedor(@RequestParam("nombre") String nombre,
                                 @RequestParam("id_proveedor") Integer id_proveedor,
                                 @RequestParam("retencion") String retencion,
                                 @RequestParam("categoria") String categoria,
                                 Model model){
        proveedorService.saveProveedor(id_proveedor,nombre,categoria,retencion);
        return "redirect:/registrarProveedor";
    }
}
