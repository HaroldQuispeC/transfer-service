package com.bootcamp.transferservice.model;

import lombok.Data;

@Data
public class IncomeAccountType {
  private String incomeAccountTypeID;
  private String incomeAccountDescription;
  private Integer maximumProductsAllowed;
}