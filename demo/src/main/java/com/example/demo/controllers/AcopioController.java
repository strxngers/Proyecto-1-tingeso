package com.example.demo.controllers;

import com.example.demo.services.AcopioService;
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
public class AcopioController {

    @Autowired
    private AcopioService acopioData;

    @GetMapping("/uploadAcopioData")
    public String acopio(){
        //acopioData.getAcopioByFecha();
        return "uploadAcopioData";
    }

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        acopioData.guardar(file);
        acopioData.leerCsv("Acopio.csv");
        return "redirect:/uploadAcopioData";
    }


}
