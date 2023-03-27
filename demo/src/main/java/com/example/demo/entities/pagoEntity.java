package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class pagoEntity {
    @Id
    private Integer id_pago;
    private Integer monto;
    private Date fecha;
    @NonNull
    @ManyToOne
    @JoinColumn(name="id_proveedor", nullable=false)
    private proveedorEntity proveedor;

}
