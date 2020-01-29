package com.sandbox.lamadatahubadapter.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Membership extends BasicEntity {

  @Column(nullable = false)
  private Long personId;

  @Column(nullable = false)
  private Long projectId;

  private LocalDate dateStart;

  private LocalDate dateFinish;

  private String responsibility;

  @Enumerated(EnumType.STRING)
  private ResponsibilityCategory responsibilityCategory;

  @ManyToOne(optional = false)
  @JoinColumn(name = "personId", insertable = false, updatable = false)
  private Person person;

  @ManyToOne(optional = false)
  @JoinColumn(name = "projectId", insertable = false, updatable = false)
  private Project project;
}
