package com.example.demo.controllers;

import com.example.demo.entities.proveedorEntity;
import com.example.demo.services.proveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping
public class proveedorController {
    @Autowired
    private proveedorService proveedorService;
/*
    @GetMapping("/proveedores")
    public String listado(Model model){
        ArrayList<proveedorEntity> proveedores = proveedorService.listaProveedores();
        model.addAttribute("proveedores",proveedores);
        return "index";
    }
*/
    @GetMapping("/lista")
    public String cualquiercosa(){
        return "index";
    }

    @PostMapping("/index")
    public String nuevoProveedor(@RequestParam("nombre") String nombre,
                                 @RequestParam("id_proveedor") Integer id_proveedor,
                                 @RequestParam("retencion") String retencion,
                                 @RequestParam("categoria") String categoria){
        proveedorService.saveProveedor(id_proveedor,nombre,categoria,retencion);
        return "redirect:/index";
    }
}
