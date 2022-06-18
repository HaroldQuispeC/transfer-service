package com.bootcamp.transferservice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "CreditTransfer")
public class CreditTransfer {
  @Id
  private long creditTransferID;
  private String creditID;
  private String clientID;
  private CreditTransferType creditTransferType;
  private LocalDateTime creditTransferDate;
  private double creditTransferAmount;
  private double previousBalance;
  private double newBalance;
  private String status;
  private boolean active;
}
