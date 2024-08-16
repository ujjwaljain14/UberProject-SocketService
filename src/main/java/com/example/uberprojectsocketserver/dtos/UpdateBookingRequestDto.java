package com.example.uberprojectsocketserver.dtos;

import com.example.uberprojectentityservice.models.BookingStatus;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateBookingRequestDto {

    private BookingStatus status;

    private Optional<Long> driverId;
}
