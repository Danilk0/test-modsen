package com.example.demomodsen.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Organizer extends BaseEntity<Integer>{

    private String name;

    @OneToMany(mappedBy = "organizer",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Event> events;
}
