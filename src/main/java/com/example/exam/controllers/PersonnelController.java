package com.example.exam.controllers;

import com.example.exam.Repositories.PersonnelRepository;
import com.example.exam.Repositories.ZoneRepository;
import com.example.exam.entities.Parking;
import com.example.exam.entities.Personnel;
import com.example.exam.entities.Zone;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/personnel")

public class PersonnelController {
    @Autowired
    private PersonnelRepository persorepo;
    @Autowired
    private ZoneRepository zrepo;
    @PostMapping(value = "/add")
    public Personnel ajouterPersonnel(@RequestBody Personnel personnel){
        System.out.println(personnel);
        return persorepo.save(personnel);
    }
    @PutMapping(value = "/affect/{idz}/{idg}")
    void affecterPersonnelZone(@PathVariable Integer idz,@PathVariable Integer idg){
        Zone zone=zrepo.findById(idz).orElse(null);
        Personnel perso=persorepo.findById(idg).orElse(null);


        zone.getPersonnels().add(perso);

        zrepo.save(zone);
    }
    //@GetMapping(value="/getbyparking")
    //public List<Personnel> getAllPersonnelByParking(@RequestBody Parking parking){
      // return persorepo.getPersonnelbyparking(parking);
   //}
    //@GetMapping(value = "/nbrgarde/{adresse}")
    //public Integer nombreGardeJour(@PathVariable String adresse){
        //return persorepo.nombreGardeJour(adresse);
    //}
@GetMapping("/getpersoDatebetween/{startDate}/{endDate}")
public List<Personnel> getPersonalByDate(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        return persorepo.findByDateDeRecrutementBetween(startDate,endDate);
}

@Scheduled(fixedRate = 30000)
    public void getNbrGardesByZone(){
    zrepo.findAll().stream().forEach(
            zone-> log.info(
                    "zone"+zone.getId()+" : "+
                            zone.getPersonnels().size()
            )
    );
}


}
