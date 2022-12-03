package com.example.demomodsen.integration.mapper.place;

import com.example.demomodsen.dto.place.PlaceCreateEditDto;
import com.example.demomodsen.database.entity.Place;
import com.example.demomodsen.integration.annotaion.IT;
import com.example.demomodsen.mapper.place.PlaceCreateEditMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class PlaceCreateEditMapperTest {

    private final PlaceCreateEditMapper placeCreateEditMapper;

    @Test
    void checkMappingFromEventCreateEditDtoToOrganizer() {
        Place place =new Place();
        place.setAddress("test");

        PlaceCreateEditDto placeCreateEditDto = new PlaceCreateEditDto("test");

        Place map = placeCreateEditMapper.map(placeCreateEditDto);

        assertEquals(place.getAddress(),map.getAddress());
    }
}