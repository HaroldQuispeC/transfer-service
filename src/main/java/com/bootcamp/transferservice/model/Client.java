package com.bootcamp.transferservice.model;

import java.util.List;
import lombok.Data;

@Data
public class Client {
  private String idClient;
  private String joiningDate;
  private String country;
  private String address;
  private ClientType clientType;
  private String status;
  private List<String> phones;
  private List<String> emails;
  private NaturalPerson naturalPerson;
  private Business business;
}

