package com.example.demomodsen.dto.organizer;


import javax.validation.constraints.Size;

public record OrganizerCreateEditDto( @Size(max = 64)
                                      String name) {
}
