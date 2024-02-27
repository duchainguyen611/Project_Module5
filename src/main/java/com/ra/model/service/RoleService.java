package com.ra.model.service;

import com.ra.model.entity.Enum.RoleName;
import com.ra.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {
    Role findByRoleName(RoleName name);
    List<Role> getAll();
}
