package com.example.demomodsen.mapper.place;

import com.example.demomodsen.database.entity.Place;
import com.example.demomodsen.dto.place.PlaceReadDto;
import com.example.demomodsen.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PlaceReadMapper implements Mapper<Place, PlaceReadDto> {
    @Override
    public PlaceReadDto map(Place object) {
        return new PlaceReadDto(object.getId(),
                                object.getAddress());
    }
}
