package com.exemplo.estomatologia.model;

public class Sintomas {
    private String dor;
    private String aparenciaGengiva;
    private boolean lesoes;
    private boolean tartaro;
    private boolean abscesso;

    private boolean fistula;
    private boolean sangramento;
    private boolean aumentoBolsa;
    private int profundidadeBolsa;
    private int mobilidadeDental; // 0 a 3

    // ===== Fatores de Certeza Padrão =====
    public double getFCDor() {
        switch (dor) {
            case "leve": return 0.2;
            case "média": return 0.4;
            case "moderada": return 0.6;
            case "alta": return 0.8;
            case "extrema": return 0.9;
            default: return 0.01;
        }
    }

    public double getFCAparenciaGengiva() {
        switch (aparenciaGengiva) {
            case "lisa e brilhante": return 0.2;
            case "edemaciada": return 0.6;
            default: return 0.01;
        }
    }

    public double getFCLesoes() { return lesoes ? 0.8 : 0.2; }

    public double getFCTartaro() { return tartaro ? 0.7 : 0.2; }

    public double getFCAbscesso() { return abscesso ? 0.9 : 0.2; }

    public double getFCFistula() { return fistula ? 0.9 : 0.2; }

    public double getFCSangramento() { return sangramento ? 0.6 : 0.2; }

    public double getFCAumentoBolsa() { return aumentoBolsa ? 0.6 : 0.2; }

    public double getFCProfundidadeBolsa() {
        if (profundidadeBolsa >= 7) return 0.9;
        else if (profundidadeBolsa >= 5) return 0.8;
        else if (profundidadeBolsa >= 3) return 0.5;
        else return 0.1;
    }

    public double getFCMobilidadeDental() {
        switch (mobilidadeDental) {
            case 3: return 0.9;
            case 2: return 0.8;
            case 1: return 0.5;
            case 0: return 0.2;
            default: return 0.1;
        }
    }

    // ===== Getters e Setters =====
    public String getDor() { return dor; }
    public void setDor(String dor) { this.dor = dor; }

    public String getAparenciaGengiva() { return aparenciaGengiva; }
    public void setAparenciaGengiva(String aparenciaGengiva) { this.aparenciaGengiva = aparenciaGengiva; }

    public boolean isLesoes() { return lesoes; }
    public void setLesoes(boolean lesoes) { this.lesoes = lesoes; }

    public boolean isTartaro() { return tartaro; }
    public void setTartaro(boolean tartaro) { this.tartaro = tartaro; }

    public boolean isAbscesso() { return abscesso; }
    public void setAbscesso(boolean abscesso) { this.abscesso = abscesso; }

    public boolean isFistula() { return fistula; }
    public void setFistula(boolean fistula) { this.fistula = fistula; }

    public boolean isSangramento() { return sangramento; }
    public void setSangramento(boolean sangramento) { this.sangramento = sangramento; }

    public boolean isAumentoBolsa() { return aumentoBolsa; }
    public void setAumentoBolsa(boolean aumentoBolsa) { this.aumentoBolsa = aumentoBolsa; }

    public int getProfundidadeBolsa() { return profundidadeBolsa; }
    public void setProfundidadeBolsa(int profundidadeBolsa) { this.profundidadeBolsa = profundidadeBolsa; }

    public int getMobilidadeDental() { return mobilidadeDental; }
    public void setMobilidadeDental(int mobilidadeDental) { this.mobilidadeDental = mobilidadeDental; }
}
