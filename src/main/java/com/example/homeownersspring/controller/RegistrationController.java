package com.example.homeownersspring.controller;

import com.example.homeownersspring.dto.AuthenticationRequestDto;
import com.example.homeownersspring.dto.CounterPostDto;
import com.example.homeownersspring.dto.ReadingPostDto;
import com.example.homeownersspring.dto.UserDto;
import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.CounterType;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.service.UserServiceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @ResponseBody
    @PatchMapping(value = "reset_password")
    public ResponseEntity patchPassword(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username =authenticationRequestDto.getUsername();
            User updateUser=userService.passwordReset(username);
            Map<Object, Object> response = new HashMap<>();
            response.put("message","Пароль изменен");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            Map<String, String> response = new HashMap<>();
            response.put("message","Пользователь не существует");
            return  ResponseEntity.badRequest().body(response);
        }
    }
}
