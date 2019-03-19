
package com.androidavanzado.cl05_minitwitter.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUserProfile {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("created")
    @Expose
    private String created;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseUserProfile() {
    }

    /**
     * 
     * @param id
     * @param username
     * @param created
     * @param website
     * @param email
     * @param descripcion
     * @param photoUrl
     */
    public ResponseUserProfile(Integer id, String username, String email, String descripcion, String website, String photoUrl, String created) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.descripcion = descripcion;
        this.website = website;
        this.photoUrl = photoUrl;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
