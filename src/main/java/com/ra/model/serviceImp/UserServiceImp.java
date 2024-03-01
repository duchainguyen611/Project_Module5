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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
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
                .image(user.getImage())
                .phone(user.getPhone())
                .status(true)
                .sex(user.getSex())
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
        User user = findById(id);
        user.setStatus(!user.getStatus());
        userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }


}
