package com.sandbox.lamadatahubadapter.service;

import com.sandbox.lamadatahubadapter.entities.Office;
import com.sandbox.lamadatahubadapter.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

  private final OfficeRepository repository;

  @Autowired
  public OfficeService(OfficeRepository repository) {
    this.repository = repository;
  }

  public Office findByLamaId(Long lamaId) {
    return repository.findByLamaId(lamaId);
  }

  public List<Office> saveAll(Iterable<Office> offices) {
    return repository.saveAll(offices);
  }

  public List<Office> saveAndFlush(List<Office> cities){
    for (city: cities) {
      repository.saveAndFlush(city);
    }
  }
}
