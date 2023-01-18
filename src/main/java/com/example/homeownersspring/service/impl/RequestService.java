package com.example.homeownersspring.service.impl;

import com.example.homeownersspring.dto.CounterReadingDtoForHistory;
import com.example.homeownersspring.dto.RequestDto;
import com.example.homeownersspring.model.Counter;
import com.example.homeownersspring.model.Request;
import com.example.homeownersspring.model.User;
import com.example.homeownersspring.repo.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestRepository requestRepository;

    public void save(Request request){
        requestRepository.save(request);
    }

    public List<RequestDto> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Request> requests = user.getRequestList();
        return requests.stream().map(request -> {
            RequestDto requestDto = new RequestDto();
            return requestDto.fromRequest(request);
        }).collect(Collectors.toList());
    }
}
