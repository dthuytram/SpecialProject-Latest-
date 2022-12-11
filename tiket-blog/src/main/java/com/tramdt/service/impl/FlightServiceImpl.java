package com.tramdt.service.impl;

import com.tramdt.dto.FlightDto;
import com.tramdt.dto.IDto.FlightDTO;
import com.tramdt.dto.IDto.FlightSearchDto;
import com.tramdt.dto.IDto.IFlightDto;
import com.tramdt.model.Flight;
import com.tramdt.repository.IFlightRepository;
import com.tramdt.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements IFlightService {
    Map<String, Page<Flight>> searchFlightList = new HashMap<>();
    Page departure;
    Page arrival;

    @Autowired
    private IFlightRepository repository;

    @Override
        public Map<String, Page<Flight>> searchAvailableFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, String sortOption, Pageable pageable) {
        System.out.println("====searchAvailableFlight==== ");
        System.out.println("departureDestination: " + departureDestination);
        System.out.println("arrivalDestination: " + arrivalDestination);
        System.out.println("arrivalDate: " + arrivalDate);

        sortOption =  "airline_type." + sortOption ;
        System.out.println("sortOption: " + sortOption);
        departure = repository.searchFlight(departureDestination, arrivalDestination, departureDate, "", sortOption, pageable);
        System.out.println("depart: {}" );
        System.out.println(departure);
        searchFlightList.put("oneway", departure);

        arrival = repository.searchFlight(arrivalDestination, departureDestination,"", arrivalDate, sortOption, pageable);
        System.out.println("arrival: {}" ) ;
        System.out.println(arrival);
        searchFlightList.put("twoway", arrival);

        return searchFlightList;
    }

    public Flight findById(Long id) {
        return repository.findByIdFlight(id);
    }

    @Override
    public Integer findByCodeFlight(String codeFlight) {
        return repository.finByCodeFlight(codeFlight);
    }

    @Override
    public void createFlight(FlightDto flightDto) {
        repository.createFlight(flightDto.getCodeFlight(), flightDto.getFromFlight(),
                flightDto.getToFlight(), flightDto.getDateStart(), flightDto.getDateEnd(),
                flightDto.getAirlineType(), true);
    }

    @Override
    public void updateFlight(FlightDto flightDto) {
        repository.updateFlight(flightDto.getCodeFlight(), flightDto.getFromFlight(), flightDto.getToFlight(), flightDto.getDateStart(),
                flightDto.getDateEnd(), flightDto.getAirlineType(), true, flightDto.getId());
    }

    @Override
    public Page<Flight> findAllFlight(Pageable pageable) {
        return repository.findAllFlight(pageable);
    }

    @Override
    public Page<Flight> findAllFlightNotPage(Pageable pageable) {
        return repository.findAllFlightNotPage(pageable);
    }

    @Override
    public List<Flight> searchFlightByDate(String date) {
        return repository.findFlightsByDateStartContains(date);
    }


    @Override
    public IFlightDto findById1(Long id) {
        return repository.findByIdFlight1(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<FlightSearchDto> searchFlight(String fromFlight, String toFlight, String dateStart, String dateEnd, Pageable pageable) {
        return repository.searchAllByFields(fromFlight, toFlight, dateStart, dateEnd, pageable);
    }

    @Override
    public Integer countSeat() {
        return repository.countSeat();
    }

    @Override
    public List<Flight> countFlight(String codeFlight) {
        return repository.countFlight(codeFlight);
    }

    @Override
    public List<Flight> selectTopTen() {
        return repository.selectTopTen();
    }
}