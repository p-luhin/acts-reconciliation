package com.pluhin.helper.reconciliation.web.service;

import static com.pluhin.helper.reconciliation.common.constants.ActConstants.CREDIT_1_COLUMN_NAME;
import static com.pluhin.helper.reconciliation.common.constants.ActConstants.CREDIT_2_COLUMN_NAME;
import static com.pluhin.helper.reconciliation.common.constants.ActConstants.DEBIT_1_COLUMN_NAME;
import static com.pluhin.helper.reconciliation.common.constants.ActConstants.DEBIT_2_COLUMN_NAME;

import com.pluhin.helper.reconciliation.common.dto.CheckErrorsDTO;
import com.pluhin.helper.reconciliation.common.exception.InvalidFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ActsService {

  public CheckErrorsDTO doReconciliation(MultipartFile multipartFile) {
    XSSFWorkbook workbook = getWorkbook(multipartFile);
    XSSFSheet sheet = workbook.getSheetAt(0);

    List<Double> credit1 = fetchSums(CREDIT_1_COLUMN_NAME, sheet);
    List<Double> credit2 = fetchSums(CREDIT_2_COLUMN_NAME, sheet);
    List<Double> debit1 = fetchSums(DEBIT_1_COLUMN_NAME, sheet);
    List<Double> debit2 = fetchSums(DEBIT_2_COLUMN_NAME, sheet);

    CheckErrorsDTO checkErrorsDTO = new CheckErrorsDTO();
    checkErrorsDTO.setDebit1(compare(debit1, credit2));
    checkErrorsDTO.setCredit2(compare(credit2, debit1));
    checkErrorsDTO.setCredit1(compare(credit1, debit2));
    checkErrorsDTO.setDebit2(compare(debit2, credit1));
    return checkErrorsDTO;
  }

  private List<Double> fetchSums(String name, XSSFSheet sheet) {
    List<Double> sums = new ArrayList<>();

    int cellNum = 0;

    Iterator<Cell> cellIterator = sheet.getRow(0).cellIterator();

    while (cellIterator.hasNext()) {
      Cell cell = cellIterator.next();
      if (cell.getStringCellValue().equals(name)) {
        cellNum = cell.getColumnIndex();
        break;
      }
    }

    Iterator<Row> rowIterator = sheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();

      double cellValue = Optional.ofNullable(row.getCell(cellNum))
          .map(Cell::getNumericCellValue)
          .orElse(0d);

      if (cellValue == -1d) {
        break;
      }

      if (cellValue == 0d) {
        continue;
      }

      sums.add(row.getCell(cellNum).getNumericCellValue());
    }

    return sums;
  }

  private List<Double> compare(List<Double> main, List<Double> compareTo) {
    List<Double> errors = new ArrayList<>(main);
    compareTo.forEach(errors::remove);
    return errors;
  }

  private XSSFWorkbook getWorkbook(MultipartFile file) {
    try {
      return new XSSFWorkbook(file.getInputStream());
    } catch (IOException e) {
      log.error("Unable to open file");
      throw new InvalidFileException();
    }
  }
}
