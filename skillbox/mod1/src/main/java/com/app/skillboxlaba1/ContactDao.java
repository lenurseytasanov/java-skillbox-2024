package com.app.skillboxlaba1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ContactDao {

    private final File file;

    private final Map<String, Contact> contacts;

    public ContactDao(@Value("${app.datasource}") String datasource) {
        contacts = new HashMap<>();
        file = new File(datasource);
    }

    public void deleteByEmail(String email) {
        contacts.remove(email);
    }

    public void create(Contact contact) {
        contacts.put(contact.email(), contact);
    }

    public List<Contact> findAll() {
        return contacts.values().stream().toList();
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String data = contacts.values().stream()
                    .map(ContactUtils::convertContact)
                    .collect(Collectors.joining());
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
