package com.tramdt.service.impl;

import com.tramdt.dto.IDto.IReport;
import com.tramdt.dto.IDto.IReportAirlineType;
import com.tramdt.dto.IDto.IReportEmployee;
import com.tramdt.repository.IReportRepository;
import com.tramdt.service.IReportService;
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