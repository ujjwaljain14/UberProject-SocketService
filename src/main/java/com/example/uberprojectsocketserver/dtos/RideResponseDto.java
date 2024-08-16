package com.example.uberprojectsocketserver.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideResponseDto {

    public Boolean response;

    public Long bookingId;
}
