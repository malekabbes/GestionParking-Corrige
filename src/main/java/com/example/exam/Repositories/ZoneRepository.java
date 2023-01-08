package com.example.exam.Repositories;

import com.example.exam.entities.Parking;
import com.example.exam.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    List<Zone> findZonesByParking(Parking parking);
    List<Zone> findZonesByParking_Adresse(String adresse);


}