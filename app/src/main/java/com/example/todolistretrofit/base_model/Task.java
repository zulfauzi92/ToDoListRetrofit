package com.example.todolistretrofit.base_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Task implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cheked")
    @Expose
    private int cheked;

    public Task(String title, String description, int cheked) {
        this.title = title;
        this.description = description;
        this.cheked = cheked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCheked() {
        return cheked;
    }

    public void setCheked(int cheked) {
        this.cheked = cheked;
    }
}
