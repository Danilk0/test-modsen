package com.example.demomodsen.http.controller;

import com.example.demomodsen.dto.EventFilter;
import com.example.demomodsen.dto.event.EventCreateEditDto;
import com.example.demomodsen.dto.event.EventReadDto;
import com.example.demomodsen.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RequiredArgsConstructor
@RestController
public class EventRestController {

    private final EventService eventService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventReadDto> findAll(EventFilter filter) {
        return eventService.findAll(filter);
    }

    @GetMapping("/{id}")
    public EventReadDto findById(@PathVariable("id") Integer id) {
        return eventService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EventReadDto create(@Validated @RequestBody EventCreateEditDto createDto) {
        return eventService.create(createDto);
    }

    @PutMapping("/{id}")
    public EventReadDto update(@PathVariable("id") Integer id,
                               @RequestBody @Validated EventCreateEditDto editDto) {
        return eventService.update(id, editDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return eventService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
