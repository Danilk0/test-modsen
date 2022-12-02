package com.example.demomodsen.mapper.organizer;

import com.example.demomodsen.database.entity.Organizer;
import com.example.demomodsen.dto.organizer.OrganizerReadDto;
import com.example.demomodsen.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrganizerReadMapper implements Mapper<Organizer, OrganizerReadDto> {

    @Override
    public OrganizerReadDto map(Organizer object) {
        return new OrganizerReadDto(object.getId(),
                                    object.getName());
    }
}
