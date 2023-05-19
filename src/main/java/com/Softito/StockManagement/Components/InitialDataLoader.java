package com.Softito.StockManagement.Components;

import com.Softito.StockManagement.Entity.Role;
import com.Softito.StockManagement.Entity.User;
import com.Softito.StockManagement.Service.RoleService;
import com.Softito.StockManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;

@Component
public class InitialDataLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Role roleUser = new Role();
        roleUser.setName("User");
        roleService.add(roleUser);

        User user = new User();
        user.setName("Kaan");
        user.setSurname("Köse");
        user.setEmail("kaankutay2002@hotmail.com");
        user.setRoles(Collections.singleton(roleUser));
        user.setPassword(passwordEncoder.encode("123"));
        userService.add(user);

        Role roleAdmin = new Role();
        roleAdmin.setName("Admin");
        roleService.add(roleAdmin);

        User admin = new User();
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Collections.singleton(roleAdmin));
        userService.add(admin);
    }
}
