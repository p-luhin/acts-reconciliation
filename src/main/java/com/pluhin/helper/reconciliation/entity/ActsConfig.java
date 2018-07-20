package com.pluhin.helper.reconciliation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "acts_config")
public class ActsConfig {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String actName;
  private int documentColumn;
  private int dateColumn;
  private int dataStartRow;
  private int creditColumn;
  private int debitColumn;
  private int dataEndColumn;
  private String dataEndTextPart;
}
