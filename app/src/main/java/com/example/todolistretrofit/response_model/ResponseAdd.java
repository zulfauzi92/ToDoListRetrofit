package com.example.todolistretrofit.response_model;

import com.example.todolistretrofit.model.Task;

public class ResponseAdd {

    private String status;
    private Task data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }
}
