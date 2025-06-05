package com.exemplo.estomatologia.model;

public class Evidencia {
    private final String tipo;
    private final Object valor;
    private final double fc;

    public Evidencia(String tipo, Object valor, double fc) {
        this.tipo = tipo;
        this.valor = valor;
        this.fc = fc;
    }

    public String getTipo() { return tipo; }
    public Object getValor() { return valor; }
    public double getFc() { return fc; }
}