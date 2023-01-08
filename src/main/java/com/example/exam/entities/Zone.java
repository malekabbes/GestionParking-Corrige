package com.example.exam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Zone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;
    private float dimension;
    @OneToOne(mappedBy = "zone")
    private Personnel personnel_resp;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)

    private Set<Personnel> personnels;

    @ManyToOne
    @JsonIgnore
    private Parking parking;
}
