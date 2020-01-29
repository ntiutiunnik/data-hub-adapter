package com.sandbox.lamadatahubadapter.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class City extends BasicEntity {

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "city")
  private List<Office> offices;
}
