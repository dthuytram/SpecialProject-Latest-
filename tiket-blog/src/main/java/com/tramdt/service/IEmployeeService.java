package com.codegym.service;

import com.codegym.dto.EmployeeDto;
import com.codegym.dto.EmployeeDtoCheck;
import com.codegym.dto.IDto.EmployeeFindIdDto;
import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEmployeeService {

    //    PhuHDQ
    Page<Employee> findAllEmployee(Pageable pageable);

    Optional<Employee> findEmployeeById(Long id);

    void deleteEmployee(Long id);

    Page<Employee> findEmployee(String name, String code, String email, Pageable pageable);

    //    SonDCM
    void createNewEmployee(EmployeeDto employeeDto);

    void editEmployee(EmployeeDtoCheck employeeDto);

    EmployeeFindIdDto findByID(Long id);
}
