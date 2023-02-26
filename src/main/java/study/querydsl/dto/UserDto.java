package study.querydsl.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private String name;

    private int age;

    public UserDto() {
    }

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
