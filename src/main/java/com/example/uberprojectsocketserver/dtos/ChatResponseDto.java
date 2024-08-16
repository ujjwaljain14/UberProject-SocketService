package com.example.uberprojectsocketserver.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDto {

    private String name;
    private String message;
    private String timeStamp;
}
