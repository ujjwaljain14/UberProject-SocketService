package com.example.uberprojectsocketserver.controllers;

import com.example.uberprojectentityservice.models.BookingStatus;
import com.example.uberprojectsocketserver.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/socket")
public class DriverRequestController {
    SimpMessagingTemplate simpMessagingTemplate;
    RestTemplate restTemplate;
    public DriverRequestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/newride")
    public ResponseEntity<Boolean> raiseRideRequest(RideRequestDto rideRequestDto) {
        sendDriversNewRideRequest(rideRequestDto);
        return ResponseEntity.ok(true);
    }

    // TODO: ideally request should go only to selected drivers but for simplicity we send to all

    public void sendDriversNewRideRequest(RideRequestDto requestDto){
        simpMessagingTemplate.convertAndSend("/topic/rideRequest",requestDto);
    }

    @MessageMapping("/rideresponse/{userId}")
    public synchronized void rideResponseHandler(@DestinationVariable String userId , RideResponseDto rideResponseDto){
        System.out.println(rideResponseDto.getResponse());
        UpdateBookingRequestDto requestDto = UpdateBookingRequestDto
                .builder()
                .driverId(Optional.of(Long.parseLong(userId)))
                .status(BookingStatus.SCHEDULED)
                .build();
        UpdateBookingResponseDto result = this.restTemplate.patchForObject("http://localhost:7480/api/v1/booking/"+rideResponseDto.bookingId, requestDto,UpdateBookingResponseDto.class);
    }


}
