package com.example.demo.services;

import com.example.demo.entities.calidadEntity;
import com.example.demo.repositories.calidadRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class calidadService {
    @Autowired
    calidadRepository calidadRepository;

    private final Logger logg = LoggerFactory.getLogger(calidadService.class);

    public ArrayList<calidadEntity> obtenerData(){
        return (ArrayList<calidadEntity>) calidadRepository.findAll();
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
        calidadRepository.deleteAll();
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
                    guardarDataDB(Integer.parseInt(bfRead.split(";")[0]), Integer.parseInt(bfRead.split(";")[1]), Integer.parseInt(bfRead.split(";")[2]));
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

    public void guardarData(calidadEntity data){
        calidadRepository.save(data);
    }

    public void guardarDataDB(Integer id_proveedor, Integer por_grasa, Integer por_solidos){
        calidadEntity newData = new calidadEntity();
        newData.setId_proveedor(id_proveedor);
        newData.setPor_grasa(por_grasa);
        newData.setPor_solidos(por_solidos);
        guardarData(newData);
    }


    public void eliminarData(ArrayList<calidadEntity> datas){
        calidadRepository.deleteAll(datas);
    }
}
