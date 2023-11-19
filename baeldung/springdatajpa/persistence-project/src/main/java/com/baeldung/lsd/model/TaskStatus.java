package com.baeldung.lsd.model;

public enum TaskStatus {

    TODO("To do"),
    IN_PROGRESS("In progress"),
    ON_HOLD("On hold"),
    DONE("Done");

    private final String label;

    TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }


}
