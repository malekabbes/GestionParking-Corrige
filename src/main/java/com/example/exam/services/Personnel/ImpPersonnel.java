package com.example.exam.services.Personnel;

import com.example.exam.Repositories.ParkingRepository;
import com.example.exam.Repositories.PersonnelRepository;
import com.example.exam.Repositories.ZoneRepository;
import com.example.exam.entities.Parking;
import com.example.exam.entities.Personnel;
import com.example.exam.entities.Zone;
import com.example.exam.generic.ImplementationGeneric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class ImpPersonnel implements IntPersonnel {

    @Autowired
    private ParkingRepository parkrepo;
    @Autowired
    private ZoneRepository zonerepo;
    @Autowired
    private PersonnelRepository persorepo;



}
