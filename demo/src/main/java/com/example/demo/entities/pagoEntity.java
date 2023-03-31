package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer id_proveedor;

}