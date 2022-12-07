package com.tramdt.service.sercurity.impl;

import com.tramdt.model.Role;
import com.tramdt.repository.sercurity.RoleRepository;
import com.tramdt.service.sercurity.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }
}
