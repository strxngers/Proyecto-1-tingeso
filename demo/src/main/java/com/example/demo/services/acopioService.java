package com.example.demo.services;

import com.example.demo.entities.acopioEntity;
import com.example.demo.repositories.acopioRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

@Service
public class acopioService {
    @Autowired
    acopioRepository acopioRepository;

    private final Logger logg = LoggerFactory.getLogger(acopioService.class);

    public ArrayList<acopioEntity> obtenerData(){
        return (ArrayList<acopioEntity>) acopioRepository.findAll();
    }

    @Generated
    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public void leerCsv(String direccion){
        String texto = "";
        BufferedReader bf = null;
        acopioRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], Integer.parseInt(bfRead.split(";")[2]), bfRead.split(";")[3]);
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }

    public void guardarData(acopioEntity data){
        acopioRepository.save(data);
    }

    public void guardarDataDB(String fecha, String turno, Integer id_proveedor, String kls_leche){
        acopioEntity newData = new acopioEntity();
        newData.setFecha(fecha);
        newData.setTurno(turno);
        newData.setId_proveedor(id_proveedor);
        newData.setKls_leche(kls_leche);
        guardarData(newData);
    }


    public void eliminarData(ArrayList<acopioEntity> datas){
        acopioRepository.deleteAll(datas);
    }

}
