package com.example.demomodsen.integration.mapper.place;

import com.example.demomodsen.dto.place.PlaceReadDto;
import com.example.demomodsen.database.entity.Place;
import com.example.demomodsen.integration.annotaion.IT;
import com.example.demomodsen.mapper.place.PlaceReadMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

@IT
@RequiredArgsConstructor
class PlaceReadMapperTest {

    private final PlaceReadMapper placeReadMapper;

    @Test
    void checkMappingFromOrganizerToOrganizerReadDto() {
        Place place =new Place();
        place.setId(1);
        place.setAddress("test");

        PlaceReadDto map = placeReadMapper.map(place);

        assertEquals(place.getAddress(),map.address());
        assertEquals(place.getId(),map.id());
    }
}