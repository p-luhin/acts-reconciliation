package com.pluhin.helper.reconciliation;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@Entity
@Table(name = "acts_config")
@EqualsAndHashCode(callSuper = true)
public class ActsConfig extends AbstractPersistable<Integer> {

  private String actName;
  private int documentColumn;
  private int dateColumn;
  private int dataStartRow;
  private int creditColumn;
  private int debitColumn;
  private int dataEndColumn;
  private String dataEndTextPart;
}
