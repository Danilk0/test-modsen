package com.example.demomodsen.dto.place;

import javax.validation.constraints.Size;

public record PlaceCreateEditDto( @Size(max = 64)
                                  String address) {
}
