package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // declara que es una entidad del sistema
@Table(name = "proveedor")
@Data   // Getter y setter
@AllArgsConstructor // Constructor con todos los argumentos
@NoArgsConstructor
public class proveedorEntity {
    @Id
    private Integer id_proveedor;
    private String nombre;
    private String categoria;
    private String retencion;

}
