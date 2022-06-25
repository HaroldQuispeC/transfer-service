package com.bootcamp.transferservice.model.base;

import com.bootcamp.transferservice.model.Business;
import com.bootcamp.transferservice.model.NaturalPerson;
import java.util.List;
import lombok.Data;


@Data
public class ClientBase {
  private String joiningDate;
  private String country;
  private String clientProfile;
  private String address;
  private String clientType;
  private String status;
  private List<String> phones;
  private List<String> emails;
  private NaturalPerson naturalPerson;
  private Business business;
}
