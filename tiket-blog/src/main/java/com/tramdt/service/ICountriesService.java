package com.tramdt.service;

import com.tramdt.model.Countries;

import java.util.List;

public interface ICountriesService {

   List<Countries> fillAll();


   void save(Countries countries);
}
