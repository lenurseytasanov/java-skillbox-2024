package com.app.skillboxlaba1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final ContactDao contactDao;

    public CommandLineRunnerImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void run(String... args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] command = line.split(" ");
                try {
                    switch (command[0]) {
                        case "list":
                            List<Contact> contacts = contactDao.findAll();
                            if (contacts.isEmpty()) {
                                System.out.println("Нет контактов");
                            } else {
                                contacts.forEach(System.out::println);
                            }
                            break;
                        case "add":
                            contactDao.create(ContactUtils.paresContact(command[1]));
                            System.out.println("Создан новый контакт");
                            break;
                        case "remove":
                            contactDao.deleteByEmail(command[1]);
                            System.out.println("Контакт удален");
                            break;
                        case "save":
                            contactDao.save();
                            System.out.println("Контакты сохранены в файл contacts.txt");
                            break;
                        default:
                            System.out.println("Неизвестная команда");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка ввода");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
