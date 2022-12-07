package com.tramdt.service;

import com.tramdt.model.CustomerType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICustomerTypeService {
    void save(CustomerType customerType);

    List<CustomerType> findAll();
}
