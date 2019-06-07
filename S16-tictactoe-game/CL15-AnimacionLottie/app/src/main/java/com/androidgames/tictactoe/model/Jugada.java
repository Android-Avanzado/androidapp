package com.androidgames.tictactoe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jugada {
    private String jugadorUnoId;
    private String jugadorDosId;
    private List<Integer> celdasSeleccionadas;
    private boolean turnoJugadorUno;
    private String ganadorId;
    private Date created;
    private String abandonoId;

    public Jugada() {
    }

    public Jugada(String jugadorUnoId) {
        this.jugadorUnoId = jugadorUnoId;
        this.jugadorDosId = "";
        this.celdasSeleccionadas = new ArrayList<>();
        for(int i=0; i<9; i++) {
            this.celdasSeleccionadas.add(new Integer(0));
        }
        this.turnoJugadorUno = true;
        this.created = new Date();
        this.ganadorId = "";
        this.abandonoId = "";
    }

    public String getJugadorUnoId() {
        return jugadorUnoId;
    }

    public void setJugadorUnoId(String jugadorUnoId) {
        this.jugadorUnoId = jugadorUnoId;
    }

    public String getJugadorDosId() {
        return jugadorDosId;
    }

    public void setJugadorDosId(String jugadorDosId) {
        this.jugadorDosId = jugadorDosId;
    }

    public List<Integer> getCeldasSeleccionadas() {
        return celdasSeleccionadas;
    }

    public void setCeldasSeleccionadas(List<Integer> celdasSeleccionadas) {
        this.celdasSeleccionadas = celdasSeleccionadas;
    }

    public boolean isTurnoJugadorUno() {
        return turnoJugadorUno;
    }

    public void setTurnoJugadorUno(boolean turnoJugadorUno) {
        this.turnoJugadorUno = turnoJugadorUno;
    }

    public String getGanadorId() {
        return ganadorId;
    }

    public void setGanadorId(String ganadorId) {
        this.ganadorId = ganadorId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAbandonoId() {
        return abandonoId;
    }

    public void setAbandonoId(String abandonoId) {
        this.abandonoId = abandonoId;
    }
}
