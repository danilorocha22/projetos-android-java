package com.danilorocha.pdm2_atv3_aula_10_08_21;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("all")
public class Item {
    @SerializedName("userId")
    @Expose
    public Integer userId;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("title")
    @Expose
    public String title;
    
    @SerializedName("body")
    @Expose
    public String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "\nUsuário: "+ userId +
                "\nID: "+ id +
                "\nTitulo: "+ title +
                "\nMensagem: "+ body;
    }

}
