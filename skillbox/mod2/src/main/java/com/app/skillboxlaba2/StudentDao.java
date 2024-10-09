package com.app.skillboxlaba2;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class StudentDao {

    private final Map<Long, Student> students = new HashMap<>();

    public Student save(@NonNull Student student) {
        Long id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        student.setId(id);
        students.put(id, student);
        return student;
    }

    public List<Student> findAll() {
        return students.values().stream().toList();
    }

    public void deleteById(@NonNull Long id) {
        students.remove(id);
    }

    public void deleteAll() {
        students.clear();
    }

}
