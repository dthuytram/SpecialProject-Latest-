package com.tramdt.service.impl;


import com.tramdt.model.Airport;
import com.tramdt.repository.AirportRepository;
import com.tramdt.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> getAirports() {
//        System.out.println("========service: ");
//        List<Airport> a = airportRepository.findAllAir();
//        for (Airport ar: a) {
//            System.out.println("====arrrrrrr:    === : ");
//            System.out.println( ar);
//        }
        return airportRepository.findAllAir();
    }

    @Override
    public List<Airport> findAllAirport() {
        return airportRepository.findAll();
    }
}