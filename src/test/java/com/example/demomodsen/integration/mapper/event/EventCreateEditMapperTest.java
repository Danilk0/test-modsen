package com.example.demomodsen.integration.mapper.event;

import com.example.demomodsen.dto.event.EventCreateEditDto;
import com.example.demomodsen.database.entity.Event;
import com.example.demomodsen.integration.IntegrationTestBase;
import com.example.demomodsen.database.repository.OrganizerRepository;
import com.example.demomodsen.database.repository.PlaceRepository;
import com.example.demomodsen.mapper.event.EventCreateEditMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class EventCreateEditMapperTest extends IntegrationTestBase {

    private final EventCreateEditMapper mapper;

    private final OrganizerRepository organizerRepository;

    private final PlaceRepository placeRepository;

    @Test
    void checkMappingFromEventCreateEditDtoToEvent() {
        EventCreateEditDto editDto = new EventCreateEditDto("test", "test", LocalDateTime.MIN, 1, 1);
        Event map = mapper.map(editDto);
        assertEquals(map.getTitle(),"test");
        assertEquals(map.getDesc(),"test");
        assertEquals(map.getTime(),LocalDateTime.MIN);
        assertEquals(map.getOrganizer().getName(),organizerRepository.findById(1).get().getName());
        assertEquals(map.getPlace().getAddress(),placeRepository.findById(1).get().getAddress());
    }

}