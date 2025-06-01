package com.exemplo.estomatologia.model;

public class Sintomas {
    private String dor;
    private String vermelho;
    private boolean lesoes;
    private boolean tartaro;
    private boolean abscesso;

    private double fcDor = 0.8;
    private double fcVermelho = 0.8;
    private double fcTartaro = 0.8;
    private double fcLesoes = 0.8;
    private double fcAbscesso = 0.8;

    // ===== Sintomas =====
    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getVermelho() {
        return vermelho;
    }

    public void setVermelho(String vermelho) {
        this.vermelho = vermelho;
    }

    public boolean isLesoes() {
        return lesoes;
    }

    public void setLesoes(boolean lesoes) {
        this.lesoes = lesoes;
    }

    public boolean isTartaro() {
        return tartaro;
    }

    public void setTartaro(boolean tartaro) {
        this.tartaro = tartaro;
    }

    public boolean isAbscesso() {
        return abscesso;
    }

    public void setAbscesso(boolean abscesso) {
        this.abscesso = abscesso;
    }

    // ===== Fatores de Certeza =====


    public void setFcDor(double fcDor) {
        this.fcDor = fcDor;
    }

    public void setFcVermelho(double fcVermelho) {
        this.fcVermelho = fcVermelho;
    }

    public void setFcTartaro(double fcTartaro) {
        this.fcTartaro = fcTartaro;
    }

    public void setFcLesoes(double fcLesoes) {
        this.fcLesoes = fcLesoes;
    }

    public void setFcAbscesso(double fcAbscesso) {
        this.fcAbscesso = fcAbscesso;
    }

    public double getFCDor() {
        switch (dor) {
            case "pouca": return 0.2;
            case "média": return 0.5;
            case "muita": return 0.9;
            default: return 0.0;
        }
    }

    public double getFCVermelho() {
        switch (vermelho) {
            case "pouco": return 0.2;
            case "médio": return 0.5;
            case "muito": return 0.9;
            default: return 0.0;
        }
    }

    public double getFCLesoes() {
        return lesoes ? 0.8 : 0.0;
    }

    public double getFCTartaro() {
        return tartaro ? 0.7 : 0.0;
    }

    public double getFCAbscesso() {
        return abscesso ? 1.0 : 0.0;
    }
}