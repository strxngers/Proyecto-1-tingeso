package com.example.demo.services;

import com.example.demo.entities.AcopioEntity;
import com.example.demo.entities.CalidadEntity;
import com.example.demo.entities.PagoEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.repositories.AcopioRepository;
import com.example.demo.repositories.CalidadRepository;
import com.example.demo.repositories.PagoRepository;
import com.example.demo.repositories.ProveedorRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private AcopioService acopioService;
    @Autowired
    private AcopioRepository acopioRepository;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private CalidadRepository calidadRepository;

    @Generated
    public List<PagoEntity> planillaPago(){
        planillaProveedores();
        List<PagoEntity> pagos = pagoRepository.findAll();
        return pagos;
    }
    @Generated
    public void planillaProveedores(){
        List<ProveedorEntity> proveedores = proveedorService.listaProveedores();
        for (ProveedorEntity proveedor: proveedores
             ) {
            calcularPago(proveedor.getId_proveedor());
        }
    }

    // PROBLEMAS: % DE SOLIDOS
    //            PAGO TOTAL
    @Generated
    public void calcularPago(Integer id_proveedor){
        ProveedorEntity proveedor = proveedorRepository.findByIdProveedor(id_proveedor);    // Encontrar al proveedor para poder saber cuantos kilos de leche trajo
        List<AcopioEntity> acopiosProveedor = proveedor.getAcopios();   // Listar los acopios del proveedor
        if(proveedor.getCalidad().isEmpty() || proveedor.getAcopios().isEmpty()){
            return;
        }
        CalidadEntity calidad = proveedor.getCalidad().get(0);  // saber la calidad del acopio de leche
        PagoEntity pago = new PagoEntity(); // Pago para settear
        Integer klsLeche = getKlsLeche(id_proveedor);   // kls totales
        pago.setTotalKlsLeche(klsLeche);
        Integer dias = getDiasEnvio(id_proveedor);  // días que mandó leche
        Integer porGrasa = calidad.getPor_grasa();  // grasa del acopio
        pago.setPorGrasa(porGrasa); // Cambiamos el % de grasa
        pago.setSolidosTotales(calidad.getPor_solidos()); // cambiamos el % de st
        // Info proveedor fecha
        infoProveedorFecha(proveedor,acopiosProveedor,pago);
        //
        pago.setNroDiasDeEnvio(dias);
        // Pagos
        pago.setPromDiarioKlsLeche(promKls(klsLeche, dias));
        pago.setPagoXLeche(montoCategoria(proveedor.getCategoria(),klsLeche));
        pago.setPagoXGrasa(pagoPorGrasa(porGrasa,klsLeche));
        pago.setPagoXST(pagoPorSolidos(calidad.getPor_solidos(),klsLeche));
        double bonos = bonificaciones(id_proveedor, pago.getPagoXLeche());    // Calculo de bonos
        pago.setBonoFrecuencia(bonos);
        // Pago por leche
        double pagoLeche = pagoAcopioLeche(proveedor.getCategoria(),klsLeche,porGrasa,calidad.getPor_solidos(), proveedor.getId_proveedor());
        // Variaciones
        todasLasVariaciones(id_proveedor,pago, klsLeche, porGrasa, calidad.getPor_solidos());
        // Descuentos
        descuentos(pago,pagoLeche);
        // pago total
        pagoTotal(pago, pagoLeche);
        pago.setRetencion(retencion(proveedor, pago.getPagoTotal()));
        pago.setMontoFinal(pago.getPagoTotal() - pago.getRetencion());
        if (!exist(pago)){
            pagoRepository.save(pago);
        }
    }

    public double dctoLeche(double variacion, double pagoTotal){
        Integer desc = 0;
        if (variacion >= 0 && variacion <= 8){
            desc = 0;
        }else if (variacion > 8 && variacion < 26){
            desc = 7;
        }else if (variacion > 25 && variacion < 46){
            desc = 15;
        }else if (variacion > 46){
            desc = 30;
        }
        return desc*pagoTotal;
    }

    public double dctoGrasa(double variacion, double pagoTotal){
        Integer desc = 0;
        if (variacion >= 0 && variacion <= 15){
            desc = 0;
        } else if (variacion > 15 && variacion < 26){
            desc = 12;
        } else if (variacion > 25 && variacion < 41){
            desc = 20;
        } else if (variacion > 40){
            desc = 30;
        }
        return desc*pagoTotal;
    }

    public double dctoSolidos(double variacion, double pagoTotal){
        Integer desc = 0;
        if (variacion >= 0 && variacion <= 6){
            desc = 0;
        }else if (variacion > 6 && variacion < 13){
            desc = 18;
        }else if (variacion > 12 && variacion < 36){
            desc = 27;
        }else if (variacion > 35){
            desc = 45;
        }
        return Math.round(desc*pagoTotal);
    }

    @Generated
    public double pagoAcopioLeche(String cat, Integer klsLeche, Integer porGrasa, Integer porST, Integer id_proveedor){
        Integer pagoCategoria = montoCategoria(cat,klsLeche);
        Integer pagoGrasa = pagoPorGrasa(porGrasa, klsLeche);
        Integer pagoST = pagoPorSolidos(porST, klsLeche);
        double bonos = bonificaciones(id_proveedor, pagoCategoria);
        double pagos = pagoCategoria + pagoGrasa + pagoST;
        double pagoAcopio = pagos + bonos;
        return pagoAcopio;
    }
    @Generated
    public void pagoTotal(PagoEntity pago, double pagoLeche){
        double dcto = pago.getDctoVarST()+pago.getDctoVarGrasa()+pago.getDctoVarLeche();
        double total = pagoLeche-dcto;
        if (total < 0){
            total= total*(-1);
        }
        pago.setPagoTotal(total);
    }

    @Generated
    public void infoProveedorFecha(ProveedorEntity proveedor, List<AcopioEntity> acopiosProveedor, PagoEntity pago){
        pago.setFecha(nuevaFecha(acopiosProveedor.get(0).getFecha().toString()));   // formato de fecha
        pago.setCodigo(proveedor.getId_proveedor());
        pago.setProveedor(proveedor);
        pago.setNombreProveedor(proveedor.getNombre());
    }

    @Generated
    public void todasLasVariaciones(Integer id_proveedor, PagoEntity pagos, Integer klsLeche, Integer porGrasa, Integer porST){
        List<PagoEntity> pagosProveedor = proveedorRepository.findByIdProveedor(id_proveedor).getPagos();
        if(pagosProveedor.isEmpty()){
            pagos.setVariacionLeche(0.0);
            pagos.setVarGrasa(0.0);
            pagos.setVarST(0.0);
        }else {
            Integer cant = (pagosProveedor.size() - 1);
            Double varLeche = variacion(pagosProveedor.get(cant).getTotalKlsLeche(), klsLeche);
            Double varGrasa = varGrasaySolido(pagosProveedor.get(cant).getPorGrasa(), porGrasa);
            Double varSolidos = varGrasaySolido(pagosProveedor.get(cant).getSolidosTotales(), porST);
            pagos.setVariacionLeche(varLeche);
            pagos.setVarGrasa(varGrasa);
            pagos.setVarST(varSolidos);
        }
    }

    @Generated
    public void descuentos(PagoEntity pagos, double pagoTotal){
        pagos.setDctoVarLeche(dctoLeche(pagos.getVariacionLeche(), pagoTotal));
        pagos.setDctoVarGrasa(dctoGrasa(pagos.getVarGrasa(),pagoTotal));
        pagos.setDctoVarST(dctoSolidos(pagos.getVarST(),pagoTotal));
    }

    @Generated
    public double retencion(ProveedorEntity proveedor, double pagoTotal){
        double ret = 0.0;
        if (proveedor.getRetencion().toUpperCase().equals("SI")){
            ret = Math.round(0.13*pagoTotal);
        }
        return ret;
    }

    @Generated
    public boolean exist(PagoEntity pago){
        List<PagoEntity> pagos = pagoRepository.findAll();
        boolean flag = false;
        Integer prov = pago.getProveedor().getId_proveedor();
        String fecha = pago.getFecha();
        for (PagoEntity pagoEntity : pagos) {
            if (pagoEntity.getProveedor().getId_proveedor().equals(prov) && pagoEntity.getFecha().equals(fecha)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    @Generated
    public String nuevaFecha(String fecha){
        return fecha.substring(0, fecha.length() - 2) + acopioService.quincena(fecha).toString();
    }

    @Generated
    public Integer getKlsLeche(Integer id_proveedor){
        ProveedorEntity proveedor = proveedorRepository.findByIdProveedor(id_proveedor);
        Integer kilos = 0;
        for (AcopioEntity acopio: proveedor.getAcopios()
             ) {
            kilos = kilos + acopio.getKls_leche();
        }
        return kilos;
    }

    @Generated
    public int getDiasEnvio(int idProveedor) {
        int contDias = 0;
        ProveedorEntity proveedor = proveedorRepository.findByIdProveedor(idProveedor);
        List<AcopioEntity> acopios = proveedor.getAcopios();
        for (int i = 0; i < acopios.size() - 1; i++) {
            AcopioEntity acopioA = acopios.get(i);  // Acopio actual
            AcopioEntity acopioB = acopios.get(i + 1);  // Acopio sgte
            LocalDate fechaA = acopioA.getFecha();
            LocalDate fechaB = acopioB.getFecha();
            String turnoA = acopioA.getTurno();
            String turnoB = acopioB.getTurno();
            if (fechaA.equals(fechaB) && turnoA != null && turnoB != null && !turnoA.equals(turnoB)) {
                contDias++;
            } else if (!fechaA.equals(fechaB) && turnoA != null && turnoB != null) {
                contDias++;
            }
        }
        if (contDias >15){
            contDias = 15;
        }
        return contDias;
    }

    @Generated
    public double promKls(Integer klsLeche, Integer diasEnvio) {
        double prom = klsLeche / diasEnvio;
        return prom;
    }

    @Generated
    public double bonificaciones(Integer id_proveedor, double pagoLeche) {
        List<AcopioEntity> acopProv = proveedorRepository.findByIdProveedor(id_proveedor).getAcopios();
        List<Integer> list = mrngT(acopProv);
        double bono= porcent(list.get(0), list.get(1));
        return bono*pagoLeche;
    }

    @Generated
    public List<Integer> mrngT(List<AcopioEntity> acopiosProv) {
        Integer acopiosMrng = 0;
        Integer acopiosTarde = 0;
        for (AcopioEntity acopios : acopiosProv) {
            if (acopios.getTurno().equals("M")) {
                acopiosMrng++;
            } else if (acopios.getTurno().equals("T")) {
                acopiosTarde++;
            }
        }
        List<Integer> list = List.of(acopiosMrng, acopiosTarde);
        return list;
    }


    public double porcent(int mrng, int tarde){
        if (mrng >= 10 && tarde >= 10){
            return 0.2;
        }else if (mrng >= 10 && tarde < 10){
            return 0.12;
        }else if (tarde >= 10 && mrng < 10){
            return 0.08;
        }else
            return 0.0;
    }

    @Generated
    public Integer montoCategoria(String categoria, Integer kls_leche){ // Cambiar
        if(categoria.toUpperCase().equals("A")){
            return 700*kls_leche;
        }
        if(categoria.toUpperCase().equals("B")){
            return 550*kls_leche;
        }
        if (categoria.toUpperCase().equals("C")){
            return 400*kls_leche;
        }
        if (categoria.toUpperCase().equals("D")){
            return 250*kls_leche;
        }else
        return kls_leche;
    }

    public Integer pagoPorGrasa(Integer porGrasa, Integer kls_leche){
        if(porGrasa > 0 && porGrasa <= 20){
            return kls_leche*30;
        }if(porGrasa > 20 && porGrasa <= 45){
            return kls_leche*80;
        }if(porGrasa > 45){
            return kls_leche*120;
        }else{
            return null;
        }
    }

    public Integer pagoPorSolidos(Integer porSolidos, Integer kls_leche){
        if(porSolidos > 0 && porSolidos <= 7){
            return kls_leche*-130;
        }if (porSolidos > 7 && porSolidos <= 18){
            return kls_leche*-90;
        }if(porSolidos > 18 && porSolidos <= 35) {
            return kls_leche * 95;
        }if (porSolidos > 35){
            return kls_leche * 150;
        }else{
            return null;
        }
    }
    @Generated
    public static double variacion(double valorFinal, double valorInicial) {
        double variacion = ((valorFinal - valorInicial) / valorInicial) * 100;
        return variacion;
    }

    @Generated
    public Double varGrasaySolido(Integer nuevoTotal, Integer valor_antiguo){
        Double variacion = 0.0;
        variacion = (double)valor_antiguo - (double)nuevoTotal;
        if(variacion < 0.0){
            variacion = 0.0;
        }
        return (double) Math.round(variacion);
    }





}
