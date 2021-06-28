package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.my.market.dtos.UserDto;
import ru.geekbrains.my.market.dtos.UserRegDto;
import ru.geekbrains.my.market.models.Role;
import ru.geekbrains.my.market.models.User;
import ru.geekbrains.my.market.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public void save (UserRegDto userRegDto){
        if (userRepository.findByEmail(userRegDto.getEmail()).isEmpty()){
            User user = new User();
            user.setUsername(userRegDto.getUsername());
            user.setEmail(userRegDto.getEmail());
            user.setPassword(userRegDto.getPassword());
            List<Role> roles = new ArrayList<Role>();
            Role role = roleService.findByName("ROLE_USER").get();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}