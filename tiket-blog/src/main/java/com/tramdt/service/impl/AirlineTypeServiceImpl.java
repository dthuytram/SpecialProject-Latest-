package com.tramdt.service.impl;

import com.tramdt.model.AirlineType;
import com.tramdt.repository.IAirlineTypeRepository;
import com.tramdt.service.IAirlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineTypeServiceImpl implements IAirlineTypeService {

    @Autowired
    private IAirlineTypeRepository repository;

    @Override
    public List<AirlineType> findAll() {
        return repository.findAllAirlineType();
    }

    @Override
    public AirlineType findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
