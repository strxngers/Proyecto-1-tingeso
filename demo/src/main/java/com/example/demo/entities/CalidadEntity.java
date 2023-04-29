package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedorEntity proveedor;

}
