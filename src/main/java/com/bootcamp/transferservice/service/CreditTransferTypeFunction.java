package com.bootcamp.transferservice.service;

@FunctionalInterface
public interface CreditTransferTypeFunction {

  long getCreditTransferOperator(int type);
}
