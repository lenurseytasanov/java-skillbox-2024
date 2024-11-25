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
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String content;

    @ManyToMany
    @JoinTable(
            name = "news_category",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private final Set<Category> categories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "news")
    private final Set<Comment> comments = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

}
