package com.bootcamp.transferservice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Credit {
  private String creditID;
  private String clientID;
  private IncomeAccountType incomeAccountType;
  private String creditSN;
  private double creditLimit;
  private double debt;
  private double balance;
  private int billingCycle;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String status;
  private boolean active;
}
