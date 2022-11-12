package com.example.ejercicioextra.Modelos;

import java.io.Serializable;

public class PartidoModel implements Serializable {
    private String equipo1;
    private String equipo2;
    private int goles1;
    private int goles2;
    private String info;

    public PartidoModel(String equipo1, String equipo2, int goles1, int goles2, String info) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.goles1 = goles1;
        this.goles2 = goles2;
        this.info = info;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGoles1() {
        return goles1;
    }

    public void setGoles1(int goles1) {
        this.goles1 = goles1;
    }

    public int getGoles2() {
        return goles2;
    }

    public void setGoles2(int goles2) {
        this.goles2 = goles2;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return  getEquipo1() + ": " +getGoles1() + " - " + getEquipo2() + ": " +getGoles2() + "\nRESUMEN PARTIDO\n" + getInfo() + ".";
    }
}
