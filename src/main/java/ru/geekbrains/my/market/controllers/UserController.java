package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.ProductDto;
import ru.geekbrains.my.market.dtos.UserDto;
import ru.geekbrains.my.market.dtos.UserRegDto;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.User;
import ru.geekbrains.my.market.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    public UserDto getCurrentUsername(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        return new UserDto(currentUser.getUsername(), currentUser.getEmail());
    }

    @PostMapping
    public void register(@RequestBody UserRegDto userRegDto) {
        userRegDto.setPassword(passwordEncoder.encode(userRegDto.getPassword())); // encode email to bcrypt
        userService.save(userRegDto);
    }

}
