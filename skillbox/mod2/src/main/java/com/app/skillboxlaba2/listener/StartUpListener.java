package com.app.skillboxlaba2.listener;

import com.app.skillboxlaba2.Student;
import com.app.skillboxlaba2.StudentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartUpListener {

    @Value("${app.load-users.enabled}")
    private Boolean loadUsers;

    private final StudentDao studentDao;

    @EventListener(ApplicationStartedEvent.class)
    public void loadUsers() {
        if (loadUsers) {
            studentDao.save(new Student(null, "test1", "test1", 5L));
            studentDao.save(new Student(null, "test2", "test2", 5L));
            studentDao.save(new Student(null, "test3", "test3", 5L));
            log.info("Load default users");
        }
    }

}
