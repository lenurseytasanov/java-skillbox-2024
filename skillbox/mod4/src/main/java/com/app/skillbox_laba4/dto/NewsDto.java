package com.app.skillbox_laba4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class NewsDto {

    private UUID id;

    private String title;

    private String content;

    private Set<CategoryDto> categories;

    private UserDto author;

    private Long commentsNumber;

}
