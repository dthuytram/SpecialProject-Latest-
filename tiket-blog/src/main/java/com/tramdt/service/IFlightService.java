package com.codegym.service;

import com.codegym.dto.FlightDto;
import com.codegym.dto.IDto.FlightDTO;
import com.codegym.dto.IDto.FlightSearchDto;
import com.codegym.dto.IDto.IFlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IFlightService {
    Map<String, Page<Flight>> searchAvailableFlight(String departureDestination, String arrivalDestination, String departureDate, String arrivalDate, String sortOption, Pageable pageable);

    List<Flight> searchFlightByDate(String date);

    Flight findById(Long id);

    Integer findByCodeFlight(String codeFlight);

    void createFlight(FlightDto flightDto);

    void updateFlight(FlightDto flightDto);

    Page<Flight> findAllFlight(Pageable pageable);

    Page<Flight> findAllFlightNotPage(Pageable pageable);

    IFlightDto findById1(Long id);

    void deleteById(Long id);

    Page<FlightSearchDto> searchFlight(String fromFlight,String toFlight,String dateStart,String dateEnd,Pageable pageable);

    Integer countSeat();

    List<Flight> countFlight(String codeFlight);

    List<Flight> selectTopTen();


}
