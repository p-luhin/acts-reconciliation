package com.pluhin.helper.reconciliation.common;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CheckErrorsDTO {

  private List<Double> credit1;
  private List<Double> credit2;
  private List<Double> debit1;
  private List<Double> debit2;
}
