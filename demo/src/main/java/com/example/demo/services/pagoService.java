package com.example.demo.services;

import com.example.demo.entities.AcopioEntity;
import com.example.demo.entities.PagoEntity;
import com.example.demo.repositories.PagoRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class pagoService {
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private acopioService acopioService;
    @Autowired
    private calidadService calidadService;
    @Autowired
    private proveedorService proveedorService;
    Integer pagoCatA = 700;
    Integer pagoCatB = 550;
    Integer pagoCatC = 400;
    Integer pagoCatD = 250;

    @Generated
    public ArrayList<PagoEntity> planillaPago(){
        return (ArrayList<PagoEntity>) pagoRepository.findAll();
    }

    @Generated
    public Integer montoCalidad(String calidad, String kls_leche){
        if(calidad.toUpperCase() == "A"){
            return pagoCatA*Integer.parseInt(kls_leche);
        }
        if(calidad.toUpperCase() == "B"){
            return pagoCatB*Integer.parseInt(kls_leche);
        }
        if (calidad.toUpperCase() == "C"){
            return pagoCatC*Integer.parseInt(kls_leche);
        }
        if (calidad.toUpperCase() == "D"){
            return pagoCatD*Integer.parseInt(kls_leche);
        }
        return Integer.parseInt(kls_leche);
    }


    public Integer pagoPorGrasa(Integer porGrasa, Integer kls_leche){
        if(porGrasa > 0 && porGrasa <= 20){
            return kls_leche*30;
        }if(porGrasa > 20 && porGrasa <= 45){
            return kls_leche*80;
        }if(porGrasa > 45){
            return kls_leche*120;
        }else{
            return null;
        }
    }

    public Integer pagoPorSolidos(Integer porSolidos, Integer kls_leche){
        if(porSolidos > 0 && porSolidos <= 7){
            return kls_leche*-130;
        }if(porSolidos > 7 && porSolidos <= 18){
            return kls_leche*-90;
        }if(porSolidos > 18 && porSolidos <= 35) {
            return kls_leche * 95;
        }if (porSolidos > 35){
            return kls_leche * 150;
        }else{
            return null;
        }
    }




}
