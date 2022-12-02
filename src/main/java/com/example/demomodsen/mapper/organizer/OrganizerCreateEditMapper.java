package com.example.demomodsen.mapper.organizer;

import com.example.demomodsen.database.entity.Organizer;
import com.example.demomodsen.dto.organizer.OrganizerCreateEditDto;
import com.example.demomodsen.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrganizerCreateEditMapper implements Mapper<OrganizerCreateEditDto, Organizer> {

    @Override
    public Organizer map(OrganizerCreateEditDto object) {
        Organizer organizer =new Organizer();
        copy(object,organizer);
        return organizer;
    }

    @Override
    public Organizer map(OrganizerCreateEditDto fromObject, Organizer toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    private void copy(OrganizerCreateEditDto object, Organizer organizer) {
        organizer.setName(object.name());
    }

}
