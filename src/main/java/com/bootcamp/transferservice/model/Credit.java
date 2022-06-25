package com.bootcamp.transferservice.model;

import com.bootcamp.transferservice.model.base.CreditBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Credit extends CreditBase {
  @Id
  private String creditID;
}
