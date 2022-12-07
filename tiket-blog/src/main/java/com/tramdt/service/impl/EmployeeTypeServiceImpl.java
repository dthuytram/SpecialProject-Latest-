package com.tramdt.service.impl;

import com.tramdt.model.EmployeeType;
import com.tramdt.repository.IEmployeeTypeRepository;
import com.tramdt.service.IEmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTypeServiceImpl implements IEmployeeTypeService {
    @Autowired
    IEmployeeTypeRepository iEmployeeTypeRepository;

    @Override
    public List<EmployeeType> findAllEmployeeType() {
        return iEmployeeTypeRepository.findAllEmployeeType();
    }
}
