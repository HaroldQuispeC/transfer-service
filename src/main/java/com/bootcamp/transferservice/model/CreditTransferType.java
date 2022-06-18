package com.bootcamp.transferservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "CreditTransferType")
public class CreditTransferType {
  private Long creditTransferTypeID;
  private String creditTransferName;
  private String creditTransferDescription;
}