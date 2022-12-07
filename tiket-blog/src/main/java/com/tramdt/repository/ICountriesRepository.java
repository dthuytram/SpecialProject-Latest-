package com.tramdt.repository;

import com.tramdt.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountriesRepository extends JpaRepository<Countries, Long> {

}
