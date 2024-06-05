package com.example.items.domain;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public class Item {

    @Id
    Long id;

    String description;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

