package com.example.homeownersspring.controller;

import com.example.homeownersspring.dto.CounterDto;
import com.example.homeownersspring.dto.CounterTypeDto;
import com.example.homeownersspring.dto.RequestDto;
import com.example.homeownersspring.dto.UserDto;
import com.example.homeownersspring.model.Request;
import com.example.homeownersspring.model.Status;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.service.impl.CounterService;
import com.example.homeownersspring.service.impl.CounterTypeService;
import com.example.homeownersspring.service.impl.RequestService;
import com.example.homeownersspring.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/app/requests")
public class RequestController {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;
    private final CounterTypeService counterTypeService;
    private final RequestService requestService;
    private final CounterService counterService;
    private  final CounterTypeDto counterTypeDto;
    private  final CounterDto counterDto;

    @Autowired
    public RequestController(UserService userService, CounterTypeService counterTypeService, RequestService requestService, CounterService counterService, CounterTypeDto counterTypeDto, CounterDto counterDto) {
        this.userService = userService;
        this.counterTypeService = counterTypeService;
        this.requestService = requestService;
        this.counterService = counterService;
        this.counterTypeDto = counterTypeDto;
        this.counterDto = counterDto;
    }

    @GetMapping(value = "all")
    public String getAllRequests(Model model){
        model.addAttribute("requests", requestService.getAll());
        return "request";
    }

//    @GetMapping(value = "all")
//    public List<RequestDto> getAllRequests(){
////        model.addAttribute("user", requestService.getAll());
//        return requestService.getAll();
//    }

    @GetMapping
    public String getRequests( Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        UserDto userDto= UserDto.fromUser(user);
        model.addAttribute("user", userDto);
        return "request";
    }

    @PostMapping
    public String addRequest(@RequestParam String topic,
                             @RequestParam String textAppeal,
                             @RequestParam(value = "fileUpload") MultipartFile file,
                             Map<String,Object> model) throws IOException {
        String resultFileName = null;
        if (!file.isEmpty()) {
            File uploadDir=new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile= UUID.randomUUID().toString();
            resultFileName = uuidFile+'.'+file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFileName));
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        Request request=new Request(topic,textAppeal,resultFileName, Status.NEW.getCode(),user);
        requestService.save(request);
        return "redirect:/app/requests";
    }
}
