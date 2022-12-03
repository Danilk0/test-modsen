package com.example.demomodsen.integration.mapper.organizer;

import com.example.demomodsen.dto.organizer.OrganizerReadDto;
import com.example.demomodsen.database.entity.Organizer;
import com.example.demomodsen.integration.annotaion.IT;
import com.example.demomodsen.mapper.organizer.OrganizerReadMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class OrganizerReadMapperTest {

    private final OrganizerReadMapper organizerReadMapper;

    @Test
    void checkMappingFromOrganizerToOrganizerReadDto() {
        Organizer organizer =new Organizer();
        organizer.setId(1);
        organizer.setName("test");

        OrganizerReadDto map = organizerReadMapper.map(organizer);

        assertEquals(organizer.getName(),map.name());
        assertEquals(organizer.getId(),map.id());
    }
}