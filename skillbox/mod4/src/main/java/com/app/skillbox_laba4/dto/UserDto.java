package com.app.skillbox_laba4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {

    private UUID id;

    private String login;

}
