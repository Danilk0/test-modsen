package com.example.demomodsen.service;

import com.example.demomodsen.database.repository.OrganizerRepository;
import com.example.demomodsen.dto.organizer.OrganizerCreateEditDto;
import com.example.demomodsen.dto.organizer.OrganizerReadDto;
import com.example.demomodsen.mapper.organizer.OrganizerCreateEditMapper;
import com.example.demomodsen.mapper.organizer.OrganizerReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final OrganizerReadMapper organizerReadMapper;
    private final OrganizerCreateEditMapper organizerCreateEditMapper;

    @Transactional
    public List<OrganizerReadDto> findAll() {
        return organizerRepository.findAll().stream()
                .map(organizerReadMapper::map)
                .toList();
    }

    @Transactional
    public Optional<OrganizerReadDto> findById(Integer id){
        return organizerRepository.findById(id)
                .map(organizerReadMapper::map);
    }

    @Transactional
    public OrganizerReadDto create(OrganizerCreateEditDto editDto){
        return Optional.of(editDto)
                .map(organizerCreateEditMapper::map)
                .map(organizerRepository::save)
                .map(organizerReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<OrganizerReadDto> update(Integer id, OrganizerCreateEditDto organizerCreateEditDto) {
        return organizerRepository.findById(id)
                .map(organizer -> organizerCreateEditMapper.map(organizerCreateEditDto,organizer))
                .map(organizerRepository::update)
                .map(organizerReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        var maybePlace = organizerRepository.findById(id);
        maybePlace.ifPresent(place -> organizerRepository.delete(id));
        return maybePlace.isPresent();
    }
}
