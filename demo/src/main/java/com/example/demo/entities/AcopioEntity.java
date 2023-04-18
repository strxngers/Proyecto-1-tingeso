package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "acopio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcopioEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_acopio;
    private Integer id_proveedor;
    private Integer quincena;
    private Integer kls_leche;
    private String fecha;
    private String turno;

}
