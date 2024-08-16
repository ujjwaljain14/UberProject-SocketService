package com.example.uberprojectsocketserver.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ChatRequestDto {
    private String name;
    private String message;
}
