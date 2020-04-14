package com.udemyandroid.recyclerview;

public class Restaurante {
    private String nombre;
    private String urlPhoto;
    private float valoracion;
    private String direccion;

    public Restaurante(String nombre, String urlPhoto, float valoracion, String direccion) {
        this.nombre = nombre;
        this.urlPhoto = urlPhoto;
        this.valoracion = valoracion;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
