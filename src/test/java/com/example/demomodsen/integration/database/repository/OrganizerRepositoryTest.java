package com.example.demomodsen.integration.database.repository;

import com.example.demomodsen.database.entity.Event;
import com.example.demomodsen.database.entity.Organizer;
import com.example.demomodsen.database.repository.OrganizerRepository;
import com.example.demomodsen.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizerRepositoryTest extends IntegrationTestBase {

    private final OrganizerRepository organizerRepository;

    @Autowired
    OrganizerRepositoryTest(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Test
    void save() {
        Organizer organizer= new Organizer();
        organizer.setName("itransition");

        organizerRepository.save(organizer);
        assertNotNull(organizer.getId());
    }

    @Test
    void delete() {
        Optional<Organizer> maybeOrganizer = organizerRepository.findById(1);
        assertTrue(maybeOrganizer.isPresent());
        maybeOrganizer.ifPresent(event-> organizerRepository.delete(1));
        assertTrue(organizerRepository.findById(1).isEmpty());
    }

    @Test
    void update() {
        Organizer organizer= new Organizer();
        organizer.setId(1);
        organizer.setName("itransition");

        Optional<Organizer> actualResult = Optional.ofNullable(organizerRepository.update(organizer));

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> {
            assertEquals(organizer.getName(), result.getName());
        });

    }

    @Test
    void findById() {
        Optional<Organizer> actualResult = (organizerRepository.findById(1));
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> {
            assertEquals("modsen", result.getName());
        });
    }

    @Test
    void findAll() {
        List<Organizer> organizers = organizerRepository.findAll();
        assertThat(organizers).hasSize(4);
    }
}