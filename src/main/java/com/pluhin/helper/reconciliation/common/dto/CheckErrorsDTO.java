package com.pluhin.helper.reconciliation.common.dto;

import com.pluhin.helper.reconciliation.common.act.ActItem;
import java.util.List;
import lombok.Data;

@Data
public class CheckErrorsDTO {

  private List<ActItem> errors;
}
