package ru.geekbrains.my.market.dtos;

import lombok.Data;

@Data
public class UserRegDto {
    private String username;
    private String email;
    private String password;

    public UserRegDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }
}
