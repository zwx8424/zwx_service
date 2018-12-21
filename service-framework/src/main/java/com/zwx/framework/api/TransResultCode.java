package com.zwx.framework.api;

public enum TransResultCode {
  SUCCEEDED(1), FAILED(0), INIT(-1), PARTIAL_SUCCEEDED(2);
  private Integer code;

  TransResultCode(Integer code) {
    this.code = code;
  }

  public Integer code() {
    return this.code;
  }

  public static TransResultCode getResult(Integer resultCode) {
    for (TransResultCode s : TransResultCode.values()) {
      if (s.code().equals(resultCode)) {
        return s;
      }
    }
    return SUCCEEDED;
  }

  public boolean isSucceeded() {
    return this.equals(SUCCEEDED);
  }

  public boolean isFailed() {
    return this.equals(FAILED);
  }

  public boolean isPartialSucceeded() {
    return this.equals(PARTIAL_SUCCEEDED);
  }
}
