package com.fitness.services;

import com.fitness.entities.Role;
import com.fitness.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository rep;

    public List<Role> findAll() {
        return rep.findAll();
    }

    public void save(Role role) {
        rep.save(role);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public Role get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

    public void generateDefaultIfNotExitst() {
        String[] names = {"ROLE_USER", "ROLE_ADMIN"};

        for (String name : names) {
            if (rep.findRoleByName(name) == null)
                rep.save(new Role(name));
        }
    }

    public Role getUserRoleOrCreate() {
        if (rep.findRoleByName("ROLE_USER") == null)
            rep.save(new Role("ROLE_USER"));

        return rep.findRoleByName("ROLE_USER");
    }

    public Role getAdminRoleOrCreate() {
        if (rep.findRoleByName("ROLE_ADMIN") == null)
            rep.save(new Role("ROLE_ADMIN"));

        return rep.findRoleByName("ROLE_ADMIN");
    }

    public boolean isRoleExist(String name) {
        List<Role> roles = rep.findAll();

        for (Role role : roles)
            if (role.getName().equals(name))
                return true;

        return false;
    }

}
