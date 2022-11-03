package com.requests.backend.models.responses;

import com.requests.backend.models.Recipe;
import com.requests.backend.models.User;

public class FollowResponse {
    private int result;
    private User[] users;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}
