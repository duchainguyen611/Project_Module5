package com.ra.model.serviceImp;

import com.ra.model.entity.Enum.RoleName;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.model.repository.UserRepository;
import com.ra.model.service.RoleService;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("username is exists");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        User newUser = User.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .status(true)
                .createdAt(new Date(new java.util.Date().getTime()))
                .address(user.getAddress()).
                roles(roles).
                build();
        userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public void changeStatus(Long id) {
        Optional<User> optionalUsers = findById(id);
        if (optionalUsers.isPresent()){
            User user = optionalUsers.get();
            user.setStatus(!user.getStatus());
            userRepository.save(user);
        }
    }


}
