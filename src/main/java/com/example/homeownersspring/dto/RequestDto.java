package com.example.homeownersspring.dto;

import com.example.homeownersspring.model.Request;
import com.example.homeownersspring.model.User;
import lombok.Data;


@Data
public class RequestDto {

    private Long id;
    private String topic;
    private String content;
    private String fileAddress;
    private User id_User;

    public Request toRequest(){
        Request request=new Request();
        request.setId(id);
        request.setTopic(topic);
        request.setContent(content);
        request.setFileAddress(fileAddress);
        setId_User(id_User);
        return request;
    }
     public RequestDto fromRequest(Request request){
        RequestDto requestDto=new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setTopic(request.getTopic());
        requestDto.setContent(request.getContent());
        requestDto.setFileAddress(request.getFileAddress());
        requestDto.setId_User(requestDto.getId_User());
        return  requestDto;
     }
}
