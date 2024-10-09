package com.app.skillboxlaba2.listener;

import com.app.skillboxlaba2.event.StudentCreatedEvent;
import com.app.skillboxlaba2.event.StudentDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReportingListener {

    @EventListener(StudentCreatedEvent.class)
    public void reportStudentCreated(StudentCreatedEvent event) {
        System.out.printf("Студент создан: %s%n", event.student().toString());
    }

    @EventListener(StudentDeletedEvent.class)
    public void reportStudentDeleted(StudentDeletedEvent event) {
        System.out.printf("Студент удален. ID: %s%n", event.studentId());
    }

}
