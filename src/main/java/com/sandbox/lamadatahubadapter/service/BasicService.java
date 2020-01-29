package com.sandbox.lamadatahubadapter.service;

import com.sandbox.lamadatahubadapter.repository.BasicRepository;

import java.util.List;
import java.util.stream.Collectors;


public abstract class BasicService<T> {

  protected abstract BasicRepository<T> getRepository();

  public T findByLamaId(Long lamaId) {
    return getRepository().findByLamaId(lamaId);
  }

  public List<T> saveAll(Iterable<T> entities) {
    return getRepository().saveAll(entities);
  }

  public List<T> saveAndFlush(List<T> entities) {
    return entities.stream()
        .map(getRepository()::saveAndFlush)
        .collect(Collectors.toList());
  }
}
