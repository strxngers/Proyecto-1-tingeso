package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity // declara que es una entidad del sistema
@Table(name = "proveedor")
@Data   // Getter y setter
@AllArgsConstructor // Constructor con todos los argumentos
@NoArgsConstructor
public class ProveedorEntity {
    @Id
    @Column(name = "id_proveedor")
    private Integer id_proveedor;
    private String nombre;
    private String categoria;
    private String retencion;
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<AcopioEntity> acopios;
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<CalidadEntity> calidad;
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<PagoEntity> pagos;

    public ProveedorEntity(Integer id_proveedor, String nombre, String categoria, String retencion) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.categoria = categoria;
        this.retencion = retencion;
    }
}
