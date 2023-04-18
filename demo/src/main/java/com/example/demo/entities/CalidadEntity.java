package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calidad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalidadEntity {
    @Id
    @NotNull
    @GeneratedValue
    private Integer id_calidad;
    private Integer por_grasa;
    private Integer por_solidos;
    private Integer id_proveedor;

}
