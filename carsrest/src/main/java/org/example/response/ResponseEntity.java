package org.example.response;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class ResponseEntity {
    private String status; // SUCCESS, ERROR, INNE
    private String message; // komunikat tekstowy
    private JsonElement data; // dane json pobrane z mapy

    public ResponseEntity(String message, String status, JsonElement data) {

        Gson gson = new Gson();

        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity(String message, String status) {
        this.status = status;
        this.message = message;
    }

    public ResponseEntity(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
