package com.example.homeownersspring.service.impl;

import com.example.homeownersspring.model.Request;
import com.example.homeownersspring.repo.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void save(Request request){
        requestRepository.save(request);
    }
}
