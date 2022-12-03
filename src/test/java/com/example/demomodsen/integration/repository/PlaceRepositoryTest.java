package com.example.demomodsen.integration.repository;

import com.example.demomodsen.database.entity.Place;
import com.example.demomodsen.database.repository.PlaceRepository;
import com.example.demomodsen.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlaceRepositoryTest extends IntegrationTestBase {

    private final PlaceRepository placeRepository;

    @Autowired
    PlaceRepositoryTest(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Test
    void checkPlaceSaving() {
        Place place= new Place();
        place.setAddress("test");

        placeRepository.save(place);
        assertNotNull(place.getId());
    }

    @Test
    void checkPlaceRemoving() {
        Optional<Place> maybePlace = placeRepository.findById(1);
        assertTrue(maybePlace.isPresent());
        maybePlace.ifPresent(place -> placeRepository.delete(place.getId()));
        assertTrue(placeRepository.findById(1).isEmpty());
    }

    @Test
    void checkPlaceEditing() {
        Place place= new Place();
        place.setId(1);
        place.setAddress("test");

        Optional<Place> actualResult = Optional.ofNullable(placeRepository.update(place));

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> {
            assertEquals(place.getAddress(), result.getAddress());
        });
    }

    @Test
    void checkSearchingPlaceById() {
        Optional<Place> actualResult = (placeRepository.findById(1));
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> {
            assertEquals("ItGuru", result.getAddress());
        });
    }

    @Test
    void checkSearchingAllPlaces(){
        List<Place> places = placeRepository.findAll();
        assertThat(places).hasSize(2);
    }
}