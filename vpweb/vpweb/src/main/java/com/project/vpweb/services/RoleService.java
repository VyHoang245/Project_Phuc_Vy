package com.project.vpweb.services;

import com.project.vpweb.models.Role;
import com.project.vpweb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
    public Role getRoleByName(String roleName){
        return getAllRole().stream().filter(role -> role.getName().equals(roleName)).findFirst().orElse(null);
    }

}
