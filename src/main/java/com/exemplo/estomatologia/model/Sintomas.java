package com.exemplo.estomatologia.model;

public class Sintomas {
    private String dor;
    private String vermelho;
    private boolean lesoes;
    private boolean tartaro;
    private boolean abscesso;

    // Getters e Setters
    public String getDor() { return dor; }
    public void setDor(String dor) { this.dor = dor; }

    public String getVermelho() { return vermelho; }
    public void setVermelho(String vermelho) { this.vermelho = vermelho; }

    public boolean isLesoes() { return lesoes; }
    public void setLesoes(boolean lesoes) { this.lesoes = lesoes; }

    public boolean isTartaro() { return tartaro; }
    public void setTartaro(boolean tartaro) { this.tartaro = tartaro; }

    public boolean isAbscesso() { return abscesso; }
    public void setAbscesso(boolean abscesso) { this.abscesso = abscesso; }
}
