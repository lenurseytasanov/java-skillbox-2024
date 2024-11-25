package com.app.skillbox_laba4.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;

    @OneToMany(mappedBy = "author")
    private final Set<News> news = new LinkedHashSet<>();

    @OneToMany(mappedBy = "author")
    private final Set<Comment> comments = new LinkedHashSet<>();

}
