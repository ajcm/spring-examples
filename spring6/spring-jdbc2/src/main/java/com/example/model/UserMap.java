package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class UserMap {

    private Map<String, User> userMap = new HashMap<>();

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }
}
