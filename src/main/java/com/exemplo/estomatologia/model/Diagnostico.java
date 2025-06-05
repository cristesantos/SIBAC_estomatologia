package com.exemplo.estomatologia.model;

public class Diagnostico {
    private final String nome;
    private final double fc;

    public Diagnostico(String nome, double fc) {
        this.nome = nome;
        this.fc = fc;
    }

    public String getNome() { return nome; }
    public double getFc() { return fc; }

    @Override
    public String toString() {
        return "Diagnóstico: " + nome + " (FC: " + String.format("%.2f", fc) + ")";
    }
}
