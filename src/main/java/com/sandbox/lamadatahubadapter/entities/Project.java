package com.sandbox.lamadatahubadapter.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Project extends BasicEntity {

  private Long officeId;

  private String name;

  private LocalDate dateStart;

  private LocalDate dateFinish;

  private Boolean active;

  @ManyToOne(optional = false)
  @JoinColumn(name = "officeId", insertable = false, updatable = false)
  private Office office;

  @OneToMany(mappedBy = "project")
  private List<Membership> memberships;
}
