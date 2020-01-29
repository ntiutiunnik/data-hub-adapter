package com.sandbox.lamadatahubadapter.dto;

import lombok.Data;

@Data
public class LamaOfficeDto {

  private Long id;

  private Long cityId;

  private String officeName;

  private LamaCityDto city;
}
