package com.example.demomodsen.dto.event;

import com.example.demomodsen.validation.ValidOrganizer;
import com.example.demomodsen.validation.ValidPlace;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;


public record EventCreateEditDto(@Size(max = 64)
                                 String title,
                                 @Size(max = 256)
                                 String dsc,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                 LocalDateTime time,
                                 @ValidOrganizer
                                 Integer organizer_id,
                                 @ValidPlace
                                 Integer place_id) {

}
