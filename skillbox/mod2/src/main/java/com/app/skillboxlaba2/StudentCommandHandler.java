package com.app.skillboxlaba2;

import com.app.skillboxlaba2.event.StudentCreatedEvent;
import com.app.skillboxlaba2.event.StudentDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class StudentCommandHandler {

    private final StudentDao studentDao;

    private final ApplicationEventPublisher eventPublisher;

    @ShellMethod(key = "list")
    public String getAll() {
        return studentDao.findAll().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(key = "add")
    public void create(@ShellOption String firstName, @ShellOption String lastName, @ShellOption Long age) {
        Student student = new Student(null, firstName, lastName, age);
        student = studentDao.save(student);
        eventPublisher.publishEvent(new StudentCreatedEvent(student));
    }

    @ShellMethod(key = "remove")
    public void remove(@ShellOption Long id) {
        studentDao.deleteById(id);
        eventPublisher.publishEvent(new StudentDeletedEvent(id));
    }

    @ShellMethod(key = "remove-all")
    public String clear() {
        studentDao.deleteAll();
        return "Список студентов очищен";
    }

}
