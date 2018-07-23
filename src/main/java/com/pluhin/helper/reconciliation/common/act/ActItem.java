package com.pluhin.helper.reconciliation.common.act;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class ActItem {

  private String date;
  private String document;
  private Double sum;
  private String originalFileName;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActItem actItem = (ActItem) o;

    return Objects.equals(sum, actItem.sum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sum);
  }
}
