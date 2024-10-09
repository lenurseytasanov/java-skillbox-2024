package com.app.skillboxlaba1;

public class ContactUtils {

    public static Contact paresContact(String s) {
        String[] data = s.split(";");
        return new Contact(data[0].strip(), data[1].strip(), data[2].strip());
    }

    public static String convertContact(Contact contact) {
        return "%s;%s;%s\n".formatted(contact.fullName(), contact.phoneNumber(), contact.email());
    }

}
