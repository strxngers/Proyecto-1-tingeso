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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedorEntity proveedor;
    private Integer codigo;
    private String nombreProveedor;
    private Integer totalKlsLeche;
    private Integer nroDiasDeEnvio;
    private double promDiarioKlsLeche;
    private double variacionLeche;
    private Integer porGrasa;
    private double varGrasa;
    private Integer solidosTotales;
    private double varST;
    private  double pagoXLeche;
    private  double pagoXGrasa;
    private  double pagoXST;
    private double bonoFrecuencia;
    private double dctoVarLeche;
    private double dctoVarGrasa;
    private double dctoVarST;
    private double pagoTotal;
    private double retencion;
    private double montoFinal;  // Monto final

    // creo que lo mejor es dejar la fecha como estaba en proveedor porque así podemos ver el
    // tema de la cantiadad de días en lo que mandó leche y después cambiarlos a este formato
    private String fecha; // AAAA/MM/QUINCENA
    public PagoEntity(Integer totalKlsLeche, Integer porGrasa, Integer solidosTotales) {
        this.totalKlsLeche = totalKlsLeche;
        this.porGrasa = porGrasa;
        this.solidosTotales = solidosTotales;
    }


}
