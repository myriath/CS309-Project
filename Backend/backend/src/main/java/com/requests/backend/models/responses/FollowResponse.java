package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;

public class FollowResponse {
    @Expose
    private int result;
    @Expose
    private String[] users;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}
