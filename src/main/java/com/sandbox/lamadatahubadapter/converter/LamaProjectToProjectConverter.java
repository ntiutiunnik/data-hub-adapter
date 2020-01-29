package com.sandbox.lamadatahubadapter.converter;

import com.sandbox.lamadatahubadapter.dto.LamaProjectDto;
import com.sandbox.lamadatahubadapter.entities.Office;
import com.sandbox.lamadatahubadapter.entities.Project;
import com.sandbox.lamadatahubadapter.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LamaProjectToProjectConverter implements Converter<LamaProjectDto, Project> {

  private final OfficeService officeService;

  @Autowired
  public LamaProjectToProjectConverter(OfficeService officeService) {
    this.officeService = officeService;
  }

  @Override
  public Project convert(LamaProjectDto dto) {

    final Project project = new Project();
    project.setLamaId(dto.getId());
    project.setName(dto.getName());
    project.setActive(!dto.getIsArchived());
    project.setDateStart(dto.getStartDate());
    project.setDateFinish(dto.getEndDate());

    if (dto.getOffice() != null) {
      Office office = officeService.findByLamaId(dto.getOffice().getId());
      if (office != null) {
        project.setOfficeId(office.getId());
      }
    }

    return project;
  }
}
