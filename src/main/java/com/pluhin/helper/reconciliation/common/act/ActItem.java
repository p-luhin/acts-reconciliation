package com.pluhin.helper.reconciliation.common.act;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class ActItem {

  private static final String DOCUMENT_NUMBER_REGEX = ".*№\\s*(\\d+).*";

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

    Pattern pattern = Pattern.compile(DOCUMENT_NUMBER_REGEX);
    Matcher thisMatcher = pattern.matcher(document);
    thisMatcher.matches();

    Matcher objectMatcher = pattern.matcher(actItem.document);
    objectMatcher.matches();

    String thisDocNumber = thisMatcher.group(1);
    String objectDocNumber = objectMatcher.group(1);

    return Objects.equals(sum, actItem.sum) && thisDocNumber.equals(objectDocNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sum);
  }
}
