package com.exemplo.estomatologia.model;

import java.util.ArrayList;
import java.util.List;

public class Hipotese {
    private final String nome;
    private final List<Double> fatores;

    public Hipotese(String nome) {
        this.nome = nome;
        this.fatores = new ArrayList<>();
    }

    public void atualizarFC(double fc) {
        fatores.add(fc);
    }

    public String getNome() { return nome; }

    public double getFCCombinado() {
        if (fatores.isEmpty()) return 0.0;
        double resultado = fatores.get(0);
        for (int i = 1; i < fatores.size(); i++) {
            resultado = resultado + fatores.get(i) * (1 - resultado);
        }
        return resultado;
    }
}