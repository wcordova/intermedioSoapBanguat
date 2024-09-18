package com.umg.edu.gt.progra2.HelloWorld.Dto;

public class ModelTC {
    private String fecha;
    private double referencia;

    public ModelTC(String fecha, double referencia) {
        this.fecha = fecha;
        this.referencia = referencia;
    }

    // Getters y setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getReferencia() {
        return referencia;
    }

    public void setReferencia(double referencia) {
        this.referencia = referencia;
    }
}
