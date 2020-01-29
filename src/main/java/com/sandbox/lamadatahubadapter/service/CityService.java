package com.sandbox.lamadatahubadapter.service;

import com.sandbox.lamadatahubadapter.entities.City;
import com.sandbox.lamadatahubadapter.repository.BasicRepository;
import com.sandbox.lamadatahubadapter.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService extends BasicService<City> {

  private final CityRepository repository;

  @Autowired
  public CityService(CityRepository repository) {
    this.repository = repository;
  }

  @Override
  protected BasicRepository<City> getRepository() {
    return repository;
  }

//  public City findByLamaId(Long lamaId) {
//    return repository.findByLamaId(lamaId);
//  }
//
//  public List<City> saveAll(Iterable<City> cities) {
//    return repository.saveAll(cities);
//  }
//
//  public List<City> saveAndFlush(List<City> cities) {
//    return cities.stream()
//        .map(repository::saveAndFlush)
//        .collect(Collectors.toList());
//  }
}
