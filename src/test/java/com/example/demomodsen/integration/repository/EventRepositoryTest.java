package com.example.demomodsen.integration.repository;

import com.example.demomodsen.database.entity.Event;
import com.example.demomodsen.database.repository.EventRepository;
import com.example.demomodsen.dto.EventFilter;
import com.example.demomodsen.integration.IntegrationTestBase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventRepositoryTest extends IntegrationTestBase {

    private final EventRepository eventRepository;

    @Autowired
    EventRepositoryTest(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Test
    void checkEventSaving() {
        Event event= Event.builder()
                .title("java")
                .desc("java meetup")
                .time(LocalDateTime.now())
                .build();

        eventRepository.save(event);
        assertNotNull(event.getId());
    }

    @Test
    void checkEventRemoving() {
        Optional<Event> maybeEvent = eventRepository.findById(1);
        assertTrue(maybeEvent.isPresent());
        maybeEvent.ifPresent(event-> eventRepository.delete(event.getId()));
        assertTrue(eventRepository.findById(1).isEmpty());
    }

    @Test
    void checkEventEditing() {
        Event event= Event.builder()
                .title("java")
                .desc("java meetup")
                .time(LocalDateTime.now())
                .build();

        event.setId(1);

        Optional<Event> actualResult = Optional.ofNullable(eventRepository.update(event));

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> {
            assertEquals(event.getTitle(), result.getTitle());
            assertEquals(event.getDesc(), result.getDesc());
            assertEquals(event.getTime(), result.getTime());
            assertEquals(event.getPlace(), result.getPlace());
            assertEquals(event.getOrganizer(), result.getOrganizer());

        });

    }

    @Test
    void checkSearchEventById() {
        Optional<Event> actualResult = (eventRepository.findById(1));
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> {
            assertEquals("Java meetup", result.getTitle());
            assertEquals("Open java meetup", result.getDesc());
            assertEquals(LocalDateTime.parse("2022-12-29T18:00:00"), result.getTime());
            assertEquals(1, result.getPlace().getId());
            assertEquals(1, result.getOrganizer().getId());
        });


    }

    @Test
    void checkSearchAllEvents() {
        List<Event> events = eventRepository.findAll();
        assertThat(events).hasSize(2);
    }

    @Test
    void checkSearchAllEventsByFilter() {
        List<Event> events = eventRepository.findAllByFilter(new EventFilter("Java", "", null));
        assertThat(events).hasSize(1);
    }
}