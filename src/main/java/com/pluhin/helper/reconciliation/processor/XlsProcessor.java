package com.pluhin.helper.reconciliation.processor;

import com.pluhin.helper.reconciliation.common.act.Act;
import com.pluhin.helper.reconciliation.common.act.ActItem;
import com.pluhin.helper.reconciliation.entity.ActsConfig;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class XlsProcessor {

  public final Act processWorkbook(Workbook workbook, ActsConfig config) {
    Sheet sheet = workbook.getSheetAt(0);
    Act act = new Act();
    List<ActItem> credits = act.getCredit();
    List<ActItem> debits = act.getDebit();

    Iterator<Row> rowIterator = sheet.rowIterator();
    Row row = rowIterator.next();

    while (row.getRowNum() < config.getDataStartRow()) {
      row = rowIterator.next();
    }

    while (!shouldEnd(row, config)) {
      Optional.ofNullable(getCreditIfPresent(row, config))
          .ifPresent(credits::add);

      Optional.ofNullable(getDebitIfPresent(row, config))
          .ifPresent(debits::add);
      if (rowIterator.hasNext()) {
        row = rowIterator.next();
      }
    }

    return act;
  }

  private boolean shouldEnd(Row row, ActsConfig config) {
    return Optional.ofNullable(row.getCell(config.getDataEndColumn()))
        .map(cell -> cell.getStringCellValue().toLowerCase()
            .contains(config.getDataEndTextPart().toLowerCase()))
        .orElse(false);
  }

  private ActItem getCreditIfPresent(Row row, ActsConfig config) {
    double creditSum = Optional
        .ofNullable(row.getCell(config.getCreditColumn()))
        .map(Cell::getNumericCellValue)
        .orElse(0d);

    if (creditSum == 0d) {
      return null;
    }

    return createItem(row, config, creditSum);
  }

  private ActItem getDebitIfPresent(Row row, ActsConfig config) {
    double debitSum = Optional.ofNullable(row.getCell(config.getDebitColumn()))
        .map(Cell::getNumericCellValue)
        .orElse(0d);

    if (debitSum == 0d) {
      return null;
    }

    return createItem(row, config, debitSum);
  }

  private ActItem createItem(Row row, ActsConfig config, double sum) {
    ActItem actItem = new ActItem();
    actItem.setDate(row.getCell(config.getDateColumn()).getStringCellValue());
    actItem.setDocument(row.getCell(config.getDocumentColumn()).getStringCellValue());
    actItem.setOriginalFileName(config.getActName());
    actItem.setSum(sum);
    return actItem;
  }
}
