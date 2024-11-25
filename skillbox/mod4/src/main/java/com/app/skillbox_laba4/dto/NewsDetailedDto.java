package com.app.skillbox_laba4.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class NewsDetailedDto extends NewsDto {

    public NewsDetailedDto(UUID id, String title, String content, Set<CategoryDto> categories, UserDto author, Long commentsNumber, Set<CommentDto> comments) {
        super(id, title, content, categories, author, commentsNumber);
        this.comments = comments;
    }

    private Set<CommentDto> comments;

}
