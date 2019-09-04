package com.androidgames.tictactoe.model;

public class User {
    private String name;
    private int points;
    private int partidasJugadas;

    public User() {
    }

    public User(String name, int points, int partidasJugadas) {
        this.name = name;
        this.points = points;
        this.partidasJugadas = partidasJugadas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }
}
