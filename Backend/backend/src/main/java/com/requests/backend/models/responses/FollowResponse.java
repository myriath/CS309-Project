package com.requests.backend.models.responses;

import com.requests.backend.models.Recipe;
import com.requests.backend.models.User;

public class FollowResponse {
    private int result;
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
