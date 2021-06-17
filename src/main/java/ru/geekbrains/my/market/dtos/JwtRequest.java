package ru.geekbrains.my.market.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}

// {
//    "username": "Bob",
//    "password": "100"
// }