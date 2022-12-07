package com.tramdt.service;

import com.tramdt.model.AirlineType;
import com.tramdt.model.Flight;
import com.tramdt.repository.IAirlineTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAirlineTypeService {

    List<AirlineType> findAll();
    AirlineType findById(Long id);
}
