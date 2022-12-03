package com.example.demomodsen.mapper.event;

import com.example.demomodsen.database.entity.Event;
import com.example.demomodsen.dto.event.EventReadDto;
import com.example.demomodsen.dto.organizer.OrganizerReadDto;
import com.example.demomodsen.dto.place.PlaceReadDto;
import com.example.demomodsen.mapper.Mapper;
import com.example.demomodsen.mapper.organizer.OrganizerReadMapper;
import com.example.demomodsen.mapper.place.PlaceReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EventReadMapper implements Mapper<Event, EventReadDto> {

    private final OrganizerReadMapper organizerReadMapper;
    private final PlaceReadMapper placeReadMapper;

    @Override
    public EventReadDto map(Event object) {
        OrganizerReadDto organizer = Optional.ofNullable(object.getOrganizer())
                .map(organizerReadMapper::map)
                .orElse(null);

        PlaceReadDto place = Optional.ofNullable(object.getPlace())
                .map(placeReadMapper::map)
                .orElse(null);

        return new EventReadDto(object.getId(),
                object.getTitle(),
                object.getDesc(),
                object.getTime(),
                organizer,
                place
                );
    }

}
