package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calidad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class calidadEntity {
    @Id
    private Integer id_calidad;
    private Integer por_grasa;
    private Integer por_solidos;

}
