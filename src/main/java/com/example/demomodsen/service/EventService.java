package com.example.demomodsen.service;

import com.example.demomodsen.database.repository.EventRepository;
import com.example.demomodsen.dto.event.EventCreateEditDto;
import com.example.demomodsen.dto.EventFilter;
import com.example.demomodsen.mapper.event.EventCreateEditMapper;
import com.example.demomodsen.dto.event.EventReadDto;
import com.example.demomodsen.mapper.event.EventReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventReadMapper eventReadMapper;
    private final EventCreateEditMapper eventCreateEditMapper;

    @Transactional
    public List<EventReadDto> findAll() {
        return eventRepository.findAll().stream()
                .map(eventReadMapper::map)
                .toList();
    }
    @Transactional
    public List<EventReadDto> findAll(EventFilter eventFilter) {
        return eventRepository.findAllByFilter(eventFilter).stream()
                .map(eventReadMapper::map)
                .toList();
    }

    @Transactional
    public Optional<EventReadDto> findById(Integer id){
        return eventRepository.findById(id)
                .map(eventReadMapper::map);
    }

    @Transactional
    public EventReadDto create(EventCreateEditDto editDto){
        return Optional.of(editDto)
                .map(eventCreateEditMapper::map)
                .map(eventRepository::save)
                .map(eventReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<EventReadDto> update(Integer id, EventCreateEditDto eventCreateEditDto) {
        return eventRepository.findById(id)
                .map(event -> eventCreateEditMapper.map(eventCreateEditDto,event))
                .map(eventRepository::update)
                .map(eventReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        var maybeEvent = eventRepository.findById(id);
        maybeEvent.ifPresent(event -> eventRepository.delete(id));
        return maybeEvent.isPresent();
    }
}
