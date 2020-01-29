package com.sandbox.lamadatahubadapter.converter;

import com.sandbox.lamadatahubadapter.dto.LamaOfficeDto;
import com.sandbox.lamadatahubadapter.entities.City;
import com.sandbox.lamadatahubadapter.entities.Office;
import com.sandbox.lamadatahubadapter.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LamaOfficeToOfficeConverter implements Converter<LamaOfficeDto, Office> {

  private final CityService cityService;

  @Autowired
  public LamaOfficeToOfficeConverter(CityService officeService) {
    this.cityService = officeService;
  }

  @Override
  public Office convert(LamaOfficeDto dto) {

    final Office office = new Office();
    office.setLamaId(dto.getId());
    office.setName(dto.getOfficeName());

    if (dto.getCity() != null) {
      City city = cityService.findByLamaId(dto.getCity().getId());
      if (city != null) {
        office.setCityId(city.getId());
      }
    }

    return office;
  }
}
