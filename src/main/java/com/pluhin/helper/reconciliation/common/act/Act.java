package com.pluhin.helper.reconciliation.common.act;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Act {

  private List<ActItem> credit = new ArrayList<>();
  private List<ActItem> debit = new ArrayList<>();
}
