package com.sandbox.lamadatahubadapter.converter;

import com.sandbox.lamadatahubadapter.dto.LamaCityDto;
import com.sandbox.lamadatahubadapter.entities.City;
import org.springframework.stereotype.Component;

@Component
public class LamaCityToCityConverter implements Converter<LamaCityDto, City>{

  @Override
  public City convert(LamaCityDto dto) {

    final City city = new City();
    city.setLamaId(dto.getId());
    city.setName(dto.getTitle());

    return city;
  }
}
