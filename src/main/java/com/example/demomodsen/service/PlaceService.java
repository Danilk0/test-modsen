package com.example.demomodsen.service;

import com.example.demomodsen.database.repository.PlaceRepository;
import com.example.demomodsen.dto.place.PlaceCreateEditDto;
import com.example.demomodsen.dto.place.PlaceReadDto;
import com.example.demomodsen.mapper.place.PlaceCreateEditMapper;
import com.example.demomodsen.mapper.place.PlaceReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final PlaceReadMapper placeReadMapper;

    private final PlaceCreateEditMapper placeCreateEditMapper;

    @Transactional
    public List<PlaceReadDto> findAll() {
        return placeRepository.findAll().stream()
                .map(placeReadMapper::map)
                .toList();
    }

    @Transactional
    public Optional<PlaceReadDto> findById(Integer id){
        return placeRepository.findById(id)
                .map(placeReadMapper::map);
    }

    @Transactional
    public PlaceReadDto create(PlaceCreateEditDto editDto){
        return Optional.of(editDto)
                .map(placeCreateEditMapper::map)
                .map(placeRepository::save)
                .map(placeReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<PlaceReadDto> update(Integer id, PlaceCreateEditDto placeCreateEditDto) {
        return placeRepository.findById(id)
                .map(place -> placeCreateEditMapper.map(placeCreateEditDto,place))
                .map(placeRepository::update)
                .map(placeReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        var maybePlace = placeRepository.findById(id);
        maybePlace.ifPresent(place -> placeRepository.delete(id));
        return maybePlace.isPresent();
    }
}
