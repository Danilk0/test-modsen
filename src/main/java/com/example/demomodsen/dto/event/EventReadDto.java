package com.example.demomodsen.dto.event;

import com.example.demomodsen.dto.organizer.OrganizerReadDto;
import com.example.demomodsen.dto.place.PlaceReadDto;

import java.time.LocalDateTime;

public record EventReadDto(Integer id,
                           String title,
                           String dsc,
                           LocalDateTime time,
                           OrganizerReadDto organizer,
                           PlaceReadDto place) {
}
