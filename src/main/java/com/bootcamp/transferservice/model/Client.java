package com.bootcamp.transferservice.model;

import com.bootcamp.transferservice.model.base.ClientBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Client extends ClientBase {
  private String idClient;
}
