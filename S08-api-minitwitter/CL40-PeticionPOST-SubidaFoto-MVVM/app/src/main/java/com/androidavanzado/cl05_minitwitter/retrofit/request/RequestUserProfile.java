
package com.androidavanzado.cl05_minitwitter.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestUserProfile {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestUserProfile() {
    }

    /**
     * 
     * @param username
     * @param website
     * @param email
     * @param descripcion
     * @param password
     */
    public RequestUserProfile(String username, String email, String descripcion, String website, String password) {
        super();
        this.username = username;
        this.email = email;
        this.descripcion = descripcion;
        this.website = website;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
