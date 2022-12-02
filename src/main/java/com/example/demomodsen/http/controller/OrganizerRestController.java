package com.example.demomodsen.http.controller;
import com.example.demomodsen.dto.organizer.OrganizerCreateEditDto;
import com.example.demomodsen.dto.organizer.OrganizerReadDto;
import com.example.demomodsen.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RequiredArgsConstructor
@RestController
@RequestMapping("/organizer")
public class OrganizerRestController {

    private final OrganizerService organizerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizerReadDto> findAll() {
        return organizerService.findAll();
    }

    @GetMapping("/{id}")
    public OrganizerReadDto findById(@PathVariable("id") Integer id) {
        return organizerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizerReadDto create(@RequestBody OrganizerCreateEditDto organizerCreateEditDto) {
        return organizerService.create(organizerCreateEditDto);
    }

    @PutMapping("/{id}")
    public OrganizerReadDto update(@PathVariable("id") Integer id,
                               @RequestBody OrganizerCreateEditDto organizerCreateEditDto) {
        return organizerService.update(id, organizerCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return organizerService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
