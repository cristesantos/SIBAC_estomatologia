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

/*    public double getFCCombinado() {
        if (fatores.isEmpty()) return 0.0;
        double resultado = fatores.get(0);
        for (int i = 1; i < fatores.size(); i++) {
            resultado = resultado + fatores.get(i) * (1 - resultado);
        }
        return resultado;
    }*/

    public double getFCCombinado() {
        if (fatores.isEmpty()) return 0.0;
        double resultado = fatores.get(0);
        for (int i = 1; i < fatores.size(); i++) {

            System.out.println("resultado inicio: " + resultado);
            System.out.println("fatores.get(i): " + fatores.get(i));


            if(resultado >= 0 && fatores.get(i) >=0) {
                resultado = resultado + fatores.get(i) * (1 - resultado);
            } else if(resultado <= 0 && fatores.get(i) <= 0) {
                resultado = resultado + fatores.get(i) * (1 + resultado);
            } else {
                resultado = (resultado + fatores.get(i)) / (1 - Math.min(Math.abs(resultado), Math.abs(fatores.get(i))));
            }

            // Limita o resultado para [-1, 1]
            resultado = Math.max(-1.0, Math.min(1.0, resultado));

            System.out.println("resultado final: " + resultado);
            System.out.println();
        }
        return resultado;
    }




}