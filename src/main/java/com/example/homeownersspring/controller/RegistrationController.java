package com.example.homeownersspring.controller;

import com.example.homeownersspring.dto.UserDto;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.service.UserServiceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/app/")
public class RegistrationController {

    private final UserServiceDao userService;

    public RegistrationController(UserServiceDao userService) {
        this.userService = userService;
    }

    @GetMapping("reg")
    public String registration( Model model) {
        model.addAttribute("title", "Регистрация");
        return "registration";
    }
    @PostMapping("reg")
    public String addUser(User user, Map<String,Object> model){
        UserDto userDto=new UserDto().fromUser(user);
        User userFromDB = userService.findByUsername(user.getUsername());
        if(userFromDB!=null){
            model.put("message", "Пользователь существует!");
            return "registration";
        }
        userService.register(userDto);
        return "redirect:app/auth";
    }
}
