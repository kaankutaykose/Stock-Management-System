package com.Softito.StockManagement.Controller;

import com.Softito.StockManagement.Entity.User;
import com.Softito.StockManagement.Repository.RoleRepository;
import com.Softito.StockManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/Register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "Register";
    }
    @RequestMapping(value = "/Register",method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Validated User user, BindingResult result,Model model){
        if(result.hasErrors()){
            return "Register";
        }
        User hata = userService.findEmail(user.getEmail());
        if(hata!=null){
            result.rejectValue("email",null,"This email is in use");
            return "Register";
        }
        user.setRoles(Collections.singleton(roleRepository.getById(2L)));
        System.out.println("rol verildi");
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        System.out.println("sifre hashlendi");
        userService.add(user);
        System.out.println("kayıt yapıldı");
        return "redirect:/Login";
    }
    @GetMapping("/Login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "Login";
    }

    @PostMapping("/Login")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password,Model model){
        User loginUser=userService.findEmail(email);
        if(loginUser!=null){
            if(passwordEncoder.matches(password,loginUser.getPassword())){
                System.out.println("Informations are correct.");
                return "redirect:/products";
            }
            model.addAttribute("error", "Invalid email or password.");
            System.out.println("Wrong password.");
            return "/Login";
        }
        else {
            model.addAttribute("error", "Invalid email or password.");
            System.out.println("Wrong mail.");
            return "/Login";
        }
    }

}
