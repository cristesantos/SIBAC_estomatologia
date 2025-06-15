package com.exemplo.estomatologia.model;

/*public class Evidencia {
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
}*/


public class Evidencia {
    private final String tipo;
    private final Double valorNumerico;
    private final String valorTexto;
    private final Boolean valor;
    private final double fc;

    // Construtor para valor numérico
    public Evidencia(String tipo, double valorNumerico, double fc) {
        this.tipo = tipo;
        this.valorNumerico = valorNumerico;
        this.valorTexto = null;
        this.valor = null;
        this.fc = fc;
    }

    // Construtor para valor textual
    public Evidencia(String tipo, String valorTexto, double fc) {
        this.tipo = tipo;
        this.valorNumerico = null;
        this.valorTexto = valorTexto;
        this.valor = null;
        this.fc = fc;
    }

    // Construtor para valor booleano
    public Evidencia(String tipo, boolean valor, double fc) {
        this.tipo = tipo;
        this.valorNumerico = null;
        this.valorTexto = null;
        this.valor = valor;
        this.fc = fc;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValorNumerico() {
        return valorNumerico;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public Boolean getValor() {
        return valor;
    }

    public double getFc() {
        return fc;
    }
}
