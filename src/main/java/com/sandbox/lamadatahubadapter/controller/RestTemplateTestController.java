package com.sandbox.lamadatahubadapter.controller;

import com.sandbox.lamadatahubadapter.dto.LamaProjectDto;
import com.sandbox.lamadatahubadapter.entities.City;
import com.sandbox.lamadatahubadapter.entities.Office;
import com.sandbox.lamadatahubadapter.service.CityService;
import com.sandbox.lamadatahubadapter.service.GetQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class RestTemplateTestController {

  @Autowired
  GetQueryService queryService;

  @Autowired
  CityService cityService;

  @GetMapping("testRest/{site}")
  public String getHeaderBody(Model model, @PathVariable String site) {
    final ResponseEntity<String> param = queryService.getHeaderBody(site);
    model.addAttribute("statusParam", param.getStatusCode().toString());
    model.addAttribute("headerParam", param.getHeaders().toString());
    model.addAttribute("bodyParam", param.getBody());

    return "echo";   // <--- name of .html in res/templates
  }

  @GetMapping("getBeans")
  public String getBeanNames(Model model) {
    model.addAttribute("beansList", queryService.someFunc());
    return "beans";
  }

  @GetMapping("city/id/{id}")
  public @ResponseBody
  City getCity(@PathVariable Long id) {
    return queryService.findCity(id);
  }

  @GetMapping("city/all")
  public @ResponseBody
  List<City> getAllCities() {
    return queryService.findAllCities();
  }

  @GetMapping("city/saveDummy/{name}")
  public @ResponseBody String saveCityDummy(@PathVariable String name) {
    final City city = new City();
    city.setName(name);
    city.setLamaId(1L);
    queryService.saveCity(city);

    return "Ok";
  }

  @GetMapping("office/saveDummy/{name}/{cityId}")
  public @ResponseBody String saveOfficeDummy(@PathVariable String name, @PathVariable Long cityId) {
    final Office office = new Office();
    office.setName(name);
    office.setCreated(LocalDateTime.now());
    office.setCityId(cityId);
    queryService.saveOffice(office);

    return "Ok";
  }

  @GetMapping("lama/projects")
  public @ResponseBody
  List<LamaProjectDto> getLamaProjects() {
    return queryService.getLamaProjects();
  }

  @GetMapping("lama/projects/{id}")
  public @ResponseBody
  LamaProjectDto getLamaProjects(@PathVariable String id) {
    return queryService.getLamaProject(id);
  }

  @GetMapping("test/cityByLamaId/{id}")
  public @ResponseBody
  City getCityByLamaId(@PathVariable Long id){
    return cityService.findByLamaId(id);
  }
}
