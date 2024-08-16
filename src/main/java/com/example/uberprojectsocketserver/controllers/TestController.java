package com.example.uberprojectsocketserver.controllers;

import com.example.uberprojectsocketserver.dtos.ChatRequestDto;
import com.example.uberprojectsocketserver.dtos.ChatResponseDto;
import com.example.uberprojectsocketserver.dtos.TestRequestDto;
import com.example.uberprojectsocketserver.dtos.TestResponseDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class TestController {

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponseDto pingCheck(TestRequestDto message) {
        return TestResponseDto.builder().data("Received").build();
    }

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public ChatResponseDto chatMessage(ChatRequestDto request){

        return ChatResponseDto
                .builder()
                .name(request.getName())
                .message(request.getMessage())
                .timeStamp(""+System.currentTimeMillis())
                .build();

    }


}
