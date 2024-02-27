package com.ra.model.service;

import com.ra.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    void register(User user);
    List<User> getAllUser();
    void changeStatus(Long id);
}
