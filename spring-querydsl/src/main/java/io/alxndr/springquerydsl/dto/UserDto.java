package io.alxndr.springquerydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private int age;

    public UserDto(String username, int age) {
        this.name = username;
        this.age = age;
    }

}
