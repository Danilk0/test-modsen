package com.example.demomodsen.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Event extends BaseEntity<Integer>{

    @Column
    private String title;

    @Column(name = "description")
    private String desc;

    @Column
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;


}
