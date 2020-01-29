package com.sandbox.lamadatahubadapter.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Office extends BasicEntity {

  private Long cityId;

  private String name;

  @ManyToOne(optional = false)
  @JoinColumn(name = "cityId", insertable = false, updatable = false)
  private City city;

  @OneToMany(mappedBy = "office")
  private List<Project> projects;

  @OneToMany(mappedBy = "office")
  private List<Person> persons;
}
