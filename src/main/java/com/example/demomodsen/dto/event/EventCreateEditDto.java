package com.example.demomodsen.dto.event;

import java.time.LocalDateTime;

public record EventCreateEditDto(String title,
                                 String dsc,
                                 LocalDateTime time,
                                 Integer organizer_id,
                                 Integer place_id) {
}
