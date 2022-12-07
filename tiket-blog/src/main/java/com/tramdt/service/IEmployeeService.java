package com.tramdt.service;

import com.tramdt.dto.EmployeeDto;
import com.tramdt.dto.EmployeeDtoCheck;
import com.tramdt.dto.IDto.EmployeeFindIdDto;
import com.tramdt.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEmployeeService {

    Page<Employee> findAllEmployee(Pageable pageable);

    Optional<Employee> findEmployeeById(Long id);

    void deleteEmployee(Long id);

    Page<Employee> findEmployee(String name, String code, String email, Pageable pageable);

    void createNewEmployee(EmployeeDto employeeDto);

    void editEmployee(EmployeeDtoCheck employeeDto);

    EmployeeFindIdDto findByID(Long id);
}
