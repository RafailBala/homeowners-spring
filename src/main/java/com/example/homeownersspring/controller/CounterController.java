package com.example.homeownersspring.controller;

import com.example.homeownersspring.dto.CounterDto;
import com.example.homeownersspring.dto.CounterTypeDto;
import com.example.homeownersspring.dto.UserCounterRequestDto;
import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.CounterType;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.service.impl.CounterService;
import com.example.homeownersspring.service.impl.CounterTypeService;
import com.example.homeownersspring.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/app/")
public class CounterController {

    private final UserService userService;
    private final CounterTypeService counterTypeService;
    private final CounterService counterService;
    private  CounterTypeDto counterTypeDto;
    private  CounterDto counterDto;
    @Autowired
    public CounterController(UserService userService, CounterTypeService counterTypeService, CounterService counterService) {
        this.userService = userService;
        this.counterTypeService = counterTypeService;
        this.counterService = counterService;
    }

    @GetMapping(value = "counter_readings")
    public String getCounters(@AuthenticationPrincipal User user, Model model){

        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findByUsername(auth.getName());
        UserCounterRequestDto userCounterRequestDto=UserCounterRequestDto.fromUser(user);
        if(user.getCounterList().size()!=0) {
            List<CounterDto> counterDtoList = user.getCounterList()
                                                  .stream()
                                                  .map(counterDto::fromCounter)
                                                  .collect(Collectors.toList());
            model.addAttribute("counters",counterDtoList);
        }
        if(counterTypeService.getAll().size()!=0) {
            List<CounterTypeDto> counterTypeDtoList = counterTypeService.getAll()
                                                                        .stream()
                                                                        .map(counterTypeDto::fromCounterType)
                                                                        .collect(Collectors.toList());
            model.addAttribute("counterTypes",counterTypeDtoList);
        }
        model.addAttribute("user", userCounterRequestDto);
        return "counter";
    }

   //@PostMapping(value = "counter_readings")
   //public String postCounterType(@RequestParam String nameCounterType){
   //    counterTypeService.add(nameCounterType);
   //    return "redirect:/add/counter_readings";
   //}
    @PostMapping(value = "counter_readings")
    public String postCounter(@RequestParam String nameCounterType,
                              @RequestParam String number,
                              @RequestParam int counterReading,
                              @RequestParam Long id_CounterType,
                              Model model){
        if(nameCounterType==null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByUsername(auth.getName());
            CounterType counterType = counterTypeService.findById(id_CounterType);
            Counter counter = new Counter(number, counterReading, user, counterType);
            counterService.add(counter);
        }
        else {
            counterTypeService.add(nameCounterType);
        }
        return "redirect:/add/counter_readings";
    }
}
