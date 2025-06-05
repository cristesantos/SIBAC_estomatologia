package com.exemplo.estomatologia.model;

public class Diagnostico {
    private String nome;
    private double fatorCerteza;

    public Diagnostico(String nome, double fatorCerteza) {
        this.nome = nome;
        this.fatorCerteza = fatorCerteza;
    }

    public String getNome() {
        return nome;
    }

    public double getFatorCerteza() {
        return fatorCerteza;
    }

    @Override
    public String toString() {
        return nome + " (FC: " + fatorCerteza + ")";
    }
}
