package com.example.services;

import com.example.entities.Role;
import com.example.entities.User;
import com.example.models.common.UserDto;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(UserDto user) {
        User userFromDB = userRepository.findByUserName(user.getUserName());

        if (userFromDB != null) {
            return false;
        }
        User newUser = new User();
        Optional<Role> searchRole = roleRepository.findById(2L);
        if (!searchRole.isPresent()) {
            return false;
        }
        newUser.setRoles(Collections.singleton(searchRole.get()));
        newUser.setPassword(user.getPassword());
        newUser.setUserName(user.getUserName());
        userRepository.save(newUser);
        return true;
    }

    public Boolean deleteUser(Long id) {
        Optional<User> localUser = userRepository.findById(id);
        localUser.ifPresent(userRepository::delete);
        return true;
    }
}
