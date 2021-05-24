package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.models.Role;
import ru.geekbrains.my.market.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String title) {
        return roleRepository.findByName(title);
    }

}
