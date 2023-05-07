package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "acopio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcopioEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_acopio;
    private Integer quincena;
    private Integer kls_leche;
    private LocalDate fecha;

    public String getTurno() {
        return turno;
    }

    private String turno;
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedorEntity proveedor;

    public AcopioEntity(String fechaS, String turno) {
        this.fecha = LocalDate.parse(fechaS, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.turno = turno;
    }




}
