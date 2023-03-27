package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "acopio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class acopioEntity {
    @Id
    private Integer id_acopio;
    @NonNull
    @ManyToOne
    @JoinColumn(name="id_proveedor", nullable=false)
    private proveedorEntity proveedor;
    @NonNull
    @ManyToOne
    @JoinColumn(name="id_calidad", nullable=false)
    private calidadEntity calidad;
    private Integer quincena;
    private Integer kls_leche;
    private Date fecha;
    private char turno;


}
