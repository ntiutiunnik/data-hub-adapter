package com.sandbox.lamadatahubadapter.service;

import com.sandbox.lamadatahubadapter.dto.LamaProjectDto;
import com.sandbox.lamadatahubadapter.entities.City;
import com.sandbox.lamadatahubadapter.entities.Office;
import com.sandbox.lamadatahubadapter.repository.CityRepository;
import com.sandbox.lamadatahubadapter.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GetQueryService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ApplicationContext ctx;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private OfficeRepository officeRepository;

  public ResponseEntity<String> getHeaderBody(String site) {
    return restTemplate.getForEntity("https://" + site, String.class);
  }

  public List<String> someFunc() {
    return Arrays.asList(ctx.getBeanDefinitionNames());
  }

  public City findCity(Long id) {
    return cityRepository.findById(id).orElse(new City());
  }

  public List<City> findAllCities() {
    return cityRepository.findAll();
  }

  @Transactional
  public void saveCity(City city) {
    cityRepository.save(city);
  }

  @Transactional
  public void saveOffice(Office office) {
    officeRepository.save(office);
  }

//  public List<LamaProjectDto> getLamaProjects() {
//    LamaProjectDtoList response = restTemplate
//        .getForObject("http://localhost:8080/v1/projects", LamaProjectDtoList.class);
//    Optional<LamaProjectDtoList> result = Optional.ofNullable(response);
//
//    return result.orElse(new LamaProjectDtoList()).getProjects();
//  }

  public List<LamaProjectDto> getLamaProjects() {
    ResponseEntity<List<LamaProjectDto>> response = restTemplate.exchange(
        "http://localhost:8080/v1/projects",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<LamaProjectDto>>() {});
    return response.getBody();
  }

  public LamaProjectDto getLamaProject(String id) {
    return restTemplate.getForObject("http://localhost:8080/v1/projects/" + id, LamaProjectDto.class);
  }
}