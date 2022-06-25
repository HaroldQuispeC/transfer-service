package com.bootcamp.transferservice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditCard {
  private String creditCardID;
  private String creditID;
  private String financialCompany;
  private String creditCardSN;
  private LocalDateTime expirationDate;
  private String status;
  private boolean active;
}
