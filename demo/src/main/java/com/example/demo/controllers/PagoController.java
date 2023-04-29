package com.example.demo.controllers;

import com.example.demo.entities.PagoEntity;
import com.example.demo.repositories.PagoRepository;
import com.example.demo.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class PagoController {
    @Autowired
    private PagoRepository pagoData;
    @Autowired
    private PagoService pagoService;

    @GetMapping("/pagos")
    public String listado(Model model){
        List<PagoEntity> pagos = pagoService.planillaPago();

        model.addAttribute("pagos", pagos);
        return "/planillaPagos";
    }

}
