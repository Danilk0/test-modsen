package com.example.demomodsen.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventFilter(String title,
                          String desc,
                          LocalDateTime time) {
}
