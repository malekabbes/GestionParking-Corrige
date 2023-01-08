package com.example.exam.controllers;

import com.example.exam.Repositories.ParkingRepository;
import com.example.exam.Repositories.PersonnelRepository;
import com.example.exam.Repositories.ZoneRepository;
import com.example.exam.entities.Parking;
import com.example.exam.entities.Personnel;
import com.example.exam.entities.Poste;
import com.example.exam.entities.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/parking")
public class ParkingController {
    @Autowired
    private ParkingRepository parkrepo;
    @Autowired
    private ZoneRepository zonerepo;
    @Autowired
    private PersonnelRepository persorepo;
    @PostMapping(value = "/add")
    @Transactional
    public Parking parking(@RequestBody Parking parking){
        for (Zone zone: parking.getZone_parking()){
            zone.setParking(parking);
            zonerepo.save(zone);
        }
        return parking;
    }
    @GetMapping(value = "/getallpersobyparking")
    @Transactional
    // Method 1
    List<Personnel> getAllPersonnelByParking(@RequestBody Parking parking) {
       List<Zone> listzone=zonerepo.findZonesByParking(parking);
       List<Personnel> listperso=new ArrayList<>();
        listzone.stream().forEach(
                z->listperso.addAll(z.getPersonnels())
        );
        return listperso;
    }
    // Method 2
    //List<Personnel> getAllPersonnelByParking(@RequestBody Parking parking) {
    //    List<Zone> listzone=zonerepo.findZonesByParking(parking);
    //    System.out.println(listzone);
   //     List<Personnel> listperso = new ArrayList<>();
    //    for (Zone z : listzone) {
      //      listperso.addAll(z.getPersonnels());
        //    System.out.println(listperso);
       // }
      //  return listperso;


    //}

    @GetMapping(value = "/nbrgarde/{adresse}")
    public Integer nombreGardeJour(@PathVariable String adresse){
        List<Zone> listzone=zonerepo.findZonesByParking_Adresse(adresse);
        List<Personnel> listperso = new ArrayList<>();
        for (Zone z:listzone){
            for (Personnel p:z.getPersonnels()){
                if (p.getPoste().equals(Poste.GARDE_JOUR)){
                    listperso.add(p);
                }
            }
        }
        return listperso.size();

    }
}
