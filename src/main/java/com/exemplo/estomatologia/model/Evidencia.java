package com.exemplo.estomatologia.model;

public class Evidencia {
    private String tipo;
    private String valor;
    private double fatorCerteza;

    public Evidencia(String tipo, String valor, double fatorCerteza) {
        this.tipo = tipo;
        this.valor = valor;
        this.fatorCerteza = fatorCerteza;
    }

    public String getTipo() { return tipo; }
    public String getValor() { return valor; }
    public double getFatorCerteza() { return fatorCerteza; }
}
