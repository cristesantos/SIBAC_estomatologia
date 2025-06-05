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
    private int mobilidadeDental;

    // Apenas getters e setters — nada de lógica de fator de certeza aqui
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
