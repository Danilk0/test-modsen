package com.example.demomodsen.integration.mapper.organizer;

import com.example.demomodsen.dto.organizer.OrganizerCreateEditDto;
import com.example.demomodsen.database.entity.Organizer;
import com.example.demomodsen.integration.annotaion.IT;
import com.example.demomodsen.mapper.organizer.OrganizerCreateEditMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class OrganizerCreateEditMapperTest {

    private final OrganizerCreateEditMapper organizerCreateEditMapper;

    @Test
    void checkMappingFromOrganizerCreateEditDtoToOrganizer() {
        Organizer organizer =new Organizer();
        organizer.setName("test");

        OrganizerCreateEditDto organizerCreateEditDto = new OrganizerCreateEditDto("test");

        Organizer map = organizerCreateEditMapper.map(organizerCreateEditDto);

        assertEquals(organizer.getName(),map.getName());
    }

}