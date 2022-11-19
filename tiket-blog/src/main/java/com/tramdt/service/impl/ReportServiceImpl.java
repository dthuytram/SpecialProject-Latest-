package com.codegym.service.impl;

import com.codegym.dto.IDto.IReport;
import com.codegym.dto.IDto.IReportAirlineType;
import com.codegym.dto.IDto.IReportEmployee;
import com.codegym.repository.IReportRepository;
import com.codegym.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    public IReportRepository iReportRepository;

    @Override
    public List<IReport> getAllReportPrice() {
        return iReportRepository.getAllReportPrice();
    }


    @Override
    public List<IReportEmployee> getAllReportEmployee(Integer month) {
        return iReportRepository.getAllReportEmployee(month);
    }

    @Override
    public List<IReportAirlineType> getAllAirlineType(String fromDate,String toDate) {
        return iReportRepository.getAllAirlineType(fromDate,toDate);
    }
}