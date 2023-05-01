package com.example.demo.services;

import com.example.demo.entities.AcopioEntity;
import com.example.demo.entities.CalidadEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.repositories.CalidadRepository;
import com.example.demo.repositories.ProveedorRepository;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CalidadService {
    @Autowired
    CalidadRepository calidadRepository;
    @Autowired
    ProveedorRepository proveedorRepository;

    private final Logger logg = LoggerFactory.getLogger(CalidadService.class);

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
            //System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            //System.err.println("No se encontro el archivo");
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

    @Generated
    public void guardarData(CalidadEntity data){
        calidadRepository.save(data);
    }

    @Generated
    public void guardarDataDB(Integer id_proveedor, Integer por_grasa, Integer por_solidos){
        CalidadEntity newData = new CalidadEntity();
        newData.setPor_grasa(por_grasa);
        newData.setPor_solidos(por_solidos);
        Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id_proveedor);
        newData.setProveedor(proveedor.get());
        guardarData(newData);
    }

    public boolean existe(CalidadEntity calidad){
        List<CalidadEntity> calidades = calidadRepository.findAll();
        boolean flag = false;
        Integer prov = calidad.getProveedor().getId_proveedor();
        Integer grasa = calidad.getPor_grasa();
        Integer st = calidad.getPor_solidos();
        for (CalidadEntity acalidad: calidades) {
            if(acalidad.getProveedor().getId_proveedor().equals(prov) && acalidad.getPor_grasa().equals(grasa) && acalidad.getPor_solidos().equals(st)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void delete(CalidadEntity calidad){
        if(existe(calidad)){
            calidadRepository.delete(calidad);
        }
    }

}
