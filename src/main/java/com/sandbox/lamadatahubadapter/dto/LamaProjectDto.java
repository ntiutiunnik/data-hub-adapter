package com.sandbox.lamadatahubadapter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LamaProjectDto {

  private Long id;

  private String name;

  private Boolean isArchived;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate endDate;

  private LamaOfficeDto office;
}
