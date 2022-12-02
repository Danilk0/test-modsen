package com.example.demomodsen.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Place extends BaseEntity<Integer> {

    private String address;

    @OneToMany(mappedBy = "place",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Event> events;
}
