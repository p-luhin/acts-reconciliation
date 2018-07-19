package com.pluhin.helper.reconciliation.common.exception;

import static com.pluhin.helper.reconciliation.common.constants.ErrorConstants.INVALID_FILE_ERROR;

public class InvalidFileException extends RuntimeException {

  public InvalidFileException() {
    super(INVALID_FILE_ERROR);
  }

  public InvalidFileException(Throwable cause) {
    super(INVALID_FILE_ERROR, cause);
  }
}
