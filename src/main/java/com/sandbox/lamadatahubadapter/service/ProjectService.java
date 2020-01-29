package com.sandbox.lamadatahubadapter.service;

import com.sandbox.lamadatahubadapter.entities.Project;
import com.sandbox.lamadatahubadapter.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

  private final ProjectRepository repository;

  @Autowired
  public ProjectService(ProjectRepository repository) {
    this.repository = repository;
  }

  public Project findByLamaId(Long lamaId) {
    return repository.findByLamaId(lamaId);
  }

  public List<Project> saveAll(Iterable<Project> projects) {
    return repository.saveAll(projects);
  }

  public List<Project> saveAndFlush(List<Project> cities){
    return cities.stream()
        .map(repository::saveAndFlush)
        .collect(Collectors.toList());
  }
}
