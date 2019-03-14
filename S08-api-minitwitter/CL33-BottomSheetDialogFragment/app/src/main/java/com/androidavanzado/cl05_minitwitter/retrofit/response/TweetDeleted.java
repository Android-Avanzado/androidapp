
package com.androidavanzado.cl05_minitwitter.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TweetDeleted {

    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TweetDeleted() {
    }

    /**
     * 
     * @param mensaje
     * @param user
     */
    public TweetDeleted(String mensaje, User user) {
        super();
        this.mensaje = mensaje;
        this.user = user;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
