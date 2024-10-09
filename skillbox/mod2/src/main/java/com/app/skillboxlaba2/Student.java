package com.app.skillboxlaba2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private Long id;

    private String firstName;

    private String lastName;

    private Long age;

}
