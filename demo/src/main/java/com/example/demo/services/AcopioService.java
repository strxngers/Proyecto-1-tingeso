package com.example.demo.services;

import com.example.demo.entities.AcopioEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.repositories.AcopioRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AcopioService {
    @Autowired
    AcopioRepository acopioRepository;
    @Autowired
    ProveedorRepository proveedorRepository;

    Integer mitadMes = 15;
    private final Logger logg = LoggerFactory.getLogger(AcopioService.class);

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
                    guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], Integer.parseInt(bfRead.split(";")[2]), Integer.parseInt(bfRead.split(";")[3]));
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
    public void guardarData(AcopioEntity data){
        acopioRepository.save(data);
    }

    @Generated
    public void guardarDataDB(String fecha, String turno, Integer id_proveedor, Integer kls_leche) {
        AcopioEntity newData = new AcopioEntity();
        newData.setFecha(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        newData.setQuincena(quincena(fecha));
        newData.setTurno(turno);
        newData.setKls_leche(kls_leche);
        Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id_proveedor);
        newData.setProveedor(proveedor.get());
        if(!exist(newData)){
            guardarData(newData);
        }
        //guardarData(newData);
    }

    @Generated
    public boolean exist(AcopioEntity ac){
        List<AcopioEntity> acopios = acopioRepository.findAll();
        boolean flag = false;
        Integer pac1 = ac.getProveedor().getId_proveedor();
        LocalDate fecha = ac.getFecha();
        String turno = ac.getTurno();
        for (AcopioEntity acopio : acopios) {
            if (acopio.getProveedor().getId_proveedor().equals(pac1) && acopio.getFecha().equals(fecha) && acopio.getTurno().equals(turno)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public Integer quincena(String fecha){
        if(Integer.parseInt(fecha.substring(8)) <= mitadMes){
            return 1;
        }else if(Integer.parseInt(fecha.substring(8)) >= mitadMes){
            return 2;
        }else
            return null;
    }

    @Generated
    public void eliminarData(ArrayList<AcopioEntity> datas){
        acopioRepository.deleteAll(datas);
    }

}
