package com.example.demo.controllers;

import com.example.demo.services.CalidadService;
import com.example.demo.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping
public class CalidadController {
    @Autowired
    private CalidadService calidadData;
    @Autowired
    private PagoService pagoService;

    @GetMapping("/cargarData")
    public String calidad(){
        return "uploadCalidadData";
    }

    @PostMapping("/calidadFileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        calidadData.guardar(file);
        //redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        calidadData.leerCsv("Calidad.csv");
        return "redirect:/cargarData";}


}

