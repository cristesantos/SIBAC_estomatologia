package com.exemplo.estomatologia.model;

public class Hipotese {
    private String nome;
    private double fc;

    public Hipotese(String nome) {
        this.nome = nome;
        this.fc = 0.0;
    }

    public String getNome() { return nome; }
    public double getFc() { return fc; }

    public void atualizarFC(double novoFC) {
        this.fc = this.fc + novoFC * (1 - this.fc);
    }
}
