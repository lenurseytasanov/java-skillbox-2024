package com.app.skillboxlaba1;

public record Contact(String fullName, String phoneNumber, String email) {

    @Override
    public String toString() {
        return "«%s | %s | %s»".formatted(fullName, phoneNumber, email);
    }
}
