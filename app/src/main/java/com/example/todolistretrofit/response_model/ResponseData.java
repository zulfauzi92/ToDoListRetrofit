package com.example.todolistretrofit.response_model;

import com.example.todolistretrofit.base_model.Task;

import java.util.List;

public class ResponseData {

    private String status;
    private List<Task> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }
}
