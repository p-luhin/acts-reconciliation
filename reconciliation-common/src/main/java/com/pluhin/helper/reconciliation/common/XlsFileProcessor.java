package com.pluhin.helper.reconciliation.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsFileProcessor {

  private static final String DEBIT_1_COLUMN_NAME = "Debit 1";
  private static final String DEBIT_2_COLUMN_NAME = "Debit 2";
  private static final String CREDIT_1_COLUMN_NAME = "Credit 1";
  private static final String CREDIT_2_COLUMN_NAME = "Credit 2";

  private static final String ERRORS_FILE_NAME = "errors.xls";

  private List<Double> credit1;
  private List<Double> credit2;
  private List<Double> debit1;
  private List<Double> debit2;

  public XlsFileProcessor(InputStream stream) {
    XSSFWorkbook workbook = null;
    try {
      workbook = new XSSFWorkbook(stream);
    } catch (IOException e) {
      System.out.println("IO Exception");
      throw new RuntimeException(e);
    }

    XSSFSheet sheet = workbook.getSheetAt(0);

    credit1 = fetchSums(CREDIT_1_COLUMN_NAME, sheet);
    credit2 = fetchSums(CREDIT_2_COLUMN_NAME, sheet);
    debit1 = fetchSums(DEBIT_1_COLUMN_NAME, sheet);
    debit2 = fetchSums(DEBIT_2_COLUMN_NAME, sheet);
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

    int rowError = 0;

    try {
      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        rowError = row.getRowNum();
        if (row.getCell(cellNum).getNumericCellValue() == -1.0) {
          break;
        }

        if (row.getCell(cellNum).getNumericCellValue() == 0.0) {
          continue;
        }

        sums.add(row.getCell(cellNum).getNumericCellValue());
      }
    } catch (NullPointerException e) {
      return sums;
    }

    return sums;
  }

  public CheckErrorsDTO doCheck() {
    CheckErrorsDTO checkErrorsDTO = new CheckErrorsDTO();
    checkErrorsDTO.setDebit1(compare(debit1, credit2));
    checkErrorsDTO.setCredit2(compare(credit2, debit1));
    checkErrorsDTO.setCredit1(compare(credit1, debit2));
    checkErrorsDTO.setDebit2(compare(debit2, credit1));
    return checkErrorsDTO;
  }

  private List<Double> compare(List<Double> main, List<Double> compareTo) {
    List<Double> errors = new ArrayList<>();

    for (Double number : main) {
      if (!compareTo.contains(number)) {
        errors.add(number);
      }
    }

    return errors;
  }

/*  public File createFileWithErrors(CheckErrorsDTO checkErrors) {
    FileOutputStream os = null;
    try {
       os = new FileOutputStream(ERRORS_FILE_NAME);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    XSSFWorkbook errorWorkbook = new XSSFWorkbook();
    XSSFSheet sheet = errorWorkbook.createSheet();
    Row row1 = sheet.createRow(0);
    Row row2 = sheet.createRow(1);
    Row row3 = sheet.createRow(2);
    Row row4 = sheet.createRow(3);
    writeErrorsToRow(row1, CREDIT_1_COLUMN_NAME + " -> " + DEBIT_2_COLUMN_NAME, checkErrors.getCredit1());
    writeErrorsToRow(row2, CREDIT_2_COLUMN_NAME + " -> " + DEBIT_1_COLUMN_NAME, checkErrors.getCredit2());
    writeErrorsToRow(row3, DEBIT_1_COLUMN_NAME + " -> " + CREDIT_2_COLUMN_NAME, checkErrors.getDebit1());
    writeErrorsToRow(row4, DEBIT_2_COLUMN_NAME + " -> " + CREDIT_1_COLUMN_NAME, checkErrors.getDebit2());

    try {
      errorWorkbook.write(os);
      os.flush();
      os.close();
    } catch (IOException e) {
      System.out.println("Some error with writting file");
      throw new RuntimeException(e);
    }
  }*/

  private void writeErrorsToRow(Row row, String rowName, List<Double> errorList) {
    try {
      if (errorList.isEmpty()) {
        return;
      }

      row.createCell(0);
      row.getCell(0).setCellValue(rowName);
      int counter = 1;

      for (Double number : errorList) {
        row.createCell(counter);
        row.getCell(counter++).setCellValue(number);
      }
    } catch (NullPointerException e) {
    }
  }
}
