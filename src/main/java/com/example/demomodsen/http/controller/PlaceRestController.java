package com.example.demomodsen.http.controller;

import com.example.demomodsen.dto.place.PlaceCreateEditDto;
import com.example.demomodsen.dto.place.PlaceReadDto;
import com.example.demomodsen.service.PlaceService;
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
@RequestMapping("/place")
public class PlaceRestController {

    private final PlaceService placeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlaceReadDto> findAll() {
        return placeService.findAll();
    }

    @GetMapping("/{id}")
    public PlaceReadDto findById(@PathVariable("id") Integer id) {
        return placeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceReadDto create(@Validated @RequestBody PlaceCreateEditDto createDto) {
        return placeService.create(createDto);
    }

    @PutMapping("/{id}")
    public PlaceReadDto update(@PathVariable("id") Integer id,
                               @Validated @RequestBody PlaceCreateEditDto editDto) {
        return placeService.update(id, editDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return placeService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
