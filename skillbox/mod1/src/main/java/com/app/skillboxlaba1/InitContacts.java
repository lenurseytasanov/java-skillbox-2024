package com.app.skillboxlaba1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Profile("init")
public class InitContacts {

    private final ContactDao contactDao;

    private final ResourceLoader resourceLoader;

    @Value("${app.init-data}")
    private String initData;

    public InitContacts(ContactDao contactDao, ResourceLoader resourceLoader) {
        this.contactDao = contactDao;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void inti() {
        Resource resource = resourceLoader.getResource(initData);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            reader.lines()
                    .map(s -> {
                        String[] data = s.split(";");
                        return new Contact(data[0].strip(), data[1].strip(), data[2].strip());
                    })
                    .forEach(contactDao::create);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
