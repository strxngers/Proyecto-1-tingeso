package com.example.demo.entities;

import com.sun.istack.NotNull;
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
public class PagoEntity {
    @Id
    @NotNull
    @GeneratedValue
    private Integer id_pago;
    private Integer monto;
    private Date fecha;
    private Integer id_proveedor;

}
