package com.example.demomodsen.database.repository;

import com.example.demomodsen.dto.EventFilter;
import com.example.demomodsen.database.entity.Event;

import java.util.List;

public interface EventFilterRepository {

    List<Event> findAllByFilter(EventFilter filter);

}
