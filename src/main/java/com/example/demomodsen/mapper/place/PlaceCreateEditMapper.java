package com.example.demomodsen.mapper.place;

import com.example.demomodsen.database.entity.Place;
import com.example.demomodsen.dto.place.PlaceCreateEditDto;
import com.example.demomodsen.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PlaceCreateEditMapper implements Mapper<PlaceCreateEditDto, Place> {

    @Override
    public Place map(PlaceCreateEditDto object) {
        Place place = new Place();
        copy(object,place);
        return place;
    }

    @Override
    public Place map(PlaceCreateEditDto fromObject, Place toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    private void copy(PlaceCreateEditDto object, Place place) {
        place.setAddress(object.address());
    }
}
