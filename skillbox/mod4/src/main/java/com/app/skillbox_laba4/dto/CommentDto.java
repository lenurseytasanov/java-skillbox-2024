package com.app.skillbox_laba4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CommentDto {

    private UUID id;

    private String content;

    private UserDto author;

}
