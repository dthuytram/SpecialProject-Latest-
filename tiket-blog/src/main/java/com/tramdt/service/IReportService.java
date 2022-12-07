package com.tramdt.service;

import com.tramdt.dto.IDto.IReport;
import com.tramdt.dto.IDto.IReportAirlineType;
import com.tramdt.dto.IDto.IReportEmployee;

import java.util.List;

public interface IReportService {
    List<IReport> getAllReportPrice();
    List<IReportEmployee> getAllReportEmployee(Integer month);
    List<IReportAirlineType>  getAllAirlineType(String fromDate,String toDate);
}