package com.example.demomodsen.integration.mapper.event;

import com.example.demomodsen.dto.event.EventReadDto;
import com.example.demomodsen.database.entity.Event;
import com.example.demomodsen.database.entity.Organizer;
import com.example.demomodsen.database.entity.Place;
import com.example.demomodsen.integration.annotaion.IT;
import com.example.demomodsen.mapper.event.EventReadMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@IT
class EventReadMapperTest {

    private final EventReadMapper eventReadMapper;

    @Test
    void checkMappingFromEventToEventReadDto() {
        Place place = new Place();
        place.setAddress("testAddress");
        Organizer organizer = new Organizer();
        organizer.setName("testName");
        Event event = new Event("testTitle","testDSC", LocalDateTime.MIN,organizer,place);

        EventReadDto readDto = eventReadMapper.map(event);
        assertEquals(event.getTitle(),readDto.title());
        assertEquals(event.getDesc(),readDto.dsc());
        assertEquals(event.getTime(),readDto.time());
        assertEquals(event.getOrganizer().getName(),readDto.organizer().name());
        assertEquals(event.getPlace().getAddress(),readDto.place().address());


    }
}