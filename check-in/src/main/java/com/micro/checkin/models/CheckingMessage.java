package com.micro.checkin.models;

public class CheckingMessage {
    private int id;
    private String message;

    public CheckingMessage() {
    }

    public CheckingMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CheckingMessage [id=" + id + ", message=" + message + "]";
    }
}
