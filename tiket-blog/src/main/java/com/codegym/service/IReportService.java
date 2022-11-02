package com.codegym.service;

import com.codegym.dto.IDto.IReport;
import com.codegym.dto.IDto.IReportAirlineType;
import com.codegym.dto.IDto.IReportEmployee;

import java.util.List;

public interface IReportService {
    List<IReport> getAllReportPrice();
    List<IReportEmployee> getAllReportEmployee(Integer month);
    List<IReportAirlineType>  getAllAirlineType(String fromDate,String toDate);
}