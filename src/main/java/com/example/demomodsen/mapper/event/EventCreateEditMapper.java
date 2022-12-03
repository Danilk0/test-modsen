package com.example.demomodsen.mapper.event;

import com.example.demomodsen.database.entity.Event;
import com.example.demomodsen.database.entity.Organizer;
import com.example.demomodsen.database.entity.Place;
import com.example.demomodsen.database.repository.OrganizerRepository;
import com.example.demomodsen.database.repository.PlaceRepository;
import com.example.demomodsen.dto.event.EventCreateEditDto;
import com.example.demomodsen.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EventCreateEditMapper implements Mapper<EventCreateEditDto, Event> {

    private final OrganizerRepository organizerRepository;
    private final PlaceRepository placeRepository;

    @Override
    public Event map(EventCreateEditDto object) {
        Event event=new Event();
        copy(object,event);
        return event;
    }

    @Override
    public Event map(EventCreateEditDto object, Event event){
        copy(object,event);
        return event;
    }

    private void copy(EventCreateEditDto object, Event event) {
        event.setTitle(object.title());
        event.setDesc(object.dsc());
        event.setTime(object.time());
        event.setOrganizer(getOrganizer(object.organizer_id()));
        event.setPlace(getPlace(object.place_id()));
    }


    private Organizer getOrganizer(Integer organizerId){
        return Optional.ofNullable(organizerId)
                .flatMap(organizerRepository::findById)
                .orElse(null);
    }

    private Place getPlace(Integer placeId){
        return Optional.ofNullable(placeId)
                .flatMap(placeRepository::findById)
                .orElse(null);
    }
}
