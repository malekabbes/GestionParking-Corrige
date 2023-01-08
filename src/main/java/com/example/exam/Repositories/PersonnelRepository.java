package com.example.exam.Repositories;

import com.example.exam.entities.Parking;
import com.example.exam.entities.Personnel;
import com.example.exam.entities.Poste;
import com.example.exam.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {



    //@Query("SELECT p " +
        //    "FROM Personnel p" +
         //   "INNER JOIN Zone z " +
         //   "on p.zone.id= z.id " +
          //  "WHERE z.parking=:parking"
    //)
    //List<Personnel> getPersonnelbyparking(Parking parking);

    //@Query("SELECT COUNT(p) FROM Personnel p, INNER JOIN Zone z on p.zone.id=z.id WHERE p.poste='GARDE_JOUR' AND p.zone.parking.adresse=?1")
    @Query(value = "SELECT COUNT(p) FROM Personnel p,Zone z WHERE p.poste='GARDE_JOUR' AND p.zone.parking.adresse=?1")
    Integer nombreGardeJour(String adresse);

    List<Personnel> findAllByZone(Zone zone);

    public List<Personnel> getAllByZoneParking(Parking p) ;

    List<Personnel> getPersonnelByPoste(Poste poste);

    List<Personnel> findByDateDeRecrutementBetween(Date start, Date end);
}