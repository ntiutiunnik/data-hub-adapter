package com.sandbox.lamadatahubadapter.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Person extends BasicEntity {

  private Long officeId;

  private String uuid;

  private String fullName;

  private String primarySkill;

  private Boolean active;

  @OneToMany(mappedBy = "person")
  private List<Membership> memberships;

  @ManyToOne
  @JoinColumn(name = "officeId", insertable = false, updatable = false)
  private Office office;
}
