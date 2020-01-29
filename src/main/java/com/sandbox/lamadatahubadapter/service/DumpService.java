package com.sandbox.lamadatahubadapter.service;

import com.sandbox.lamadatahubadapter.converter.Converter;
import com.sandbox.lamadatahubadapter.converter.LamaCityToCityConverter;
import com.sandbox.lamadatahubadapter.converter.LamaOfficeToOfficeConverter;
import com.sandbox.lamadatahubadapter.converter.LamaProjectToProjectConverter;
import com.sandbox.lamadatahubadapter.dto.LamaCityDto;
import com.sandbox.lamadatahubadapter.dto.LamaOfficeDto;
import com.sandbox.lamadatahubadapter.dto.LamaProjectDto;
import com.sandbox.lamadatahubadapter.entities.City;
import com.sandbox.lamadatahubadapter.entities.Office;
import com.sandbox.lamadatahubadapter.entities.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class DumpService {

  private final RestTemplate restTemplate;
  private final CityService cityService;
  private final OfficeService officeService;
  private final ProjectService projectService;
  private final LamaCityToCityConverter lamaCityToCityConverter;
  private final LamaOfficeToOfficeConverter lamaOfficeToOfficeConverter;
  private final LamaProjectToProjectConverter lamaProjectToProjectConverter;

  private final String lamaBaseUrl;

  private final Logger logger = LoggerFactory.getLogger(DumpService.class);


  @Autowired
  public DumpService(RestTemplate restTemplate,
      CityService cityService,
      OfficeService officeService,
      ProjectService projectService,
      LamaCityToCityConverter lamaCityToCityConverter,
      LamaOfficeToOfficeConverter lamaOfficeToOfficeConverter,
      LamaProjectToProjectConverter lamaProjectToProjectConverter,
      @Value("${lama.base_url}") String lamaBaseUrl) {

    this.restTemplate = restTemplate;
    this.cityService = cityService;
    this.officeService = officeService;
    this.projectService = projectService;
    this.lamaCityToCityConverter = lamaCityToCityConverter;
    this.lamaOfficeToOfficeConverter = lamaOfficeToOfficeConverter;
    this.lamaProjectToProjectConverter = lamaProjectToProjectConverter;
    this.lamaBaseUrl = lamaBaseUrl;
  }

  public Boolean dumpAll() {
    try {
      dumpCities();
      dumpOffices();
      dumpProjects();
    } catch (Exception e) {
      logger.warn("dumpAll failed", e);
      return false;
    }

    return true;
  }

  private List<City> dumpCities() {
    //return dumpTable(LamaCityDto.class, "v2/cities", lamaCityToCityConverter, cityService);
    List<LamaCityDto> cityDtos = dumpTables();

    if (cityDtos == null) {
      return Collections.emptyList();
    }

    List<City> cities = lamaCityToCityConverter.convert(cityDtos);
    cityService.saveAndFlush(cities);
    return cities;
  }



  private List<Office> dumpOffices() {

    ResponseEntity<List<LamaOfficeDto>> response = restTemplate.exchange(
        lamaBaseUrl + "v2/offices",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<LamaOfficeDto>>() {
        });

    List<LamaOfficeDto> officeDtos = response.getBody();

    if (officeDtos == null) {
      return Collections.emptyList();
    }

    List<Office> offices = lamaOfficeToOfficeConverter.convert(officeDtos);
    officeService.saveAndFlush(offices);
    return offices;
  }

  private List<Project> dumpProjects() {

    ResponseEntity<List<LamaProjectDto>> response = restTemplate.exchange(
        lamaBaseUrl + "v1/projects",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<LamaProjectDto>>() {
        });

    List<LamaProjectDto> projectDtos = response.getBody();

    if (projectDtos == null) {
      return Collections.emptyList();
    }

    List<Project> projects = lamaProjectToProjectConverter.convert(projectDtos);
    projectService.saveAndFlush(projects);
    return projects;
  }

  private <T, U> List<T> dumpTable(Class<U> dto, String endPoint, Converter<U, T> converter, BasicService<T> service){
    ResponseEntity<List<U>> response = restTemplate.exchange(
        lamaBaseUrl + endPoint,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<U>>() {
        });

    List<U> dtos = response.getBody();

    if (dtos == null) {
      return Collections.emptyList();
    }

    List<T> entities = converter.convert(dtos);
    service.saveAndFlush(entities);
    return entities;
  }
  private <T> List<T> dumpTables() {
    ResponseEntity<List<T>> response = restTemplate.exchange(
            lamaBaseUrl + "v2/cities",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<T>>() {
            });
    List<T> cityDtos = response.getBody();
    return cityDtos;
  }
}
