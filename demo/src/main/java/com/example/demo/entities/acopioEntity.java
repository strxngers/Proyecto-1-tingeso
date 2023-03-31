package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acopio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class acopioEntity {
    @Id
    private Integer id_acopio;
    private Integer id_proveedor;
    private Integer id_calidad;
    private Integer quincena;
    private Integer kls_leche;
    private Date fecha;
    private char turno;


}
