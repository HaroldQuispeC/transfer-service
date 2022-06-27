package com.bootcamp.transferservice.model;

public class Constant {

  public enum IncomeAccountTypeId {
    PERSONAL_CREDIT_ID("1"), BUSINESS_CREDIT_ID("2");
    public final String type;

    IncomeAccountTypeId(String type) {
      this.type = type;
    }
  }

  public enum CreditTransferOperator {
    DECREMENT(-1), INCREMENT(1);
    public final long type;

    CreditTransferOperator(long type) {
      this.type = type;
    }
  }

  public enum CreditTransferType {
    CHARGE(1), REFUND(2), CASH_ADVANCE(3), PENALTY(4);
    public final int type;

    CreditTransferType(int type) {
      this.type = type;
    }
  }

  public enum ClientType {
    NATURAL_PERSON("1"), BUSINESS("2");
    public final String type;

    ClientType(String type) {
      this.type = type;
    }
  }

  public enum FinancialCompany {
    VISA("VISA"), MASTERCARD("MASTERCARD");
    public final String type;

    FinancialCompany(String type) {
      this.type = type;
    }
  }

  public enum CreditSnPrefix {
    PERSONAL_CREDIT("011"), BUSINESS_CREDIT("012");
    public final String type;

    CreditSnPrefix(String type) {
      this.type = type;
    }
  }

  public enum CreditCardSnPrefix {
    VISA("4285"), MASTERCARD("5254");
    public final String type;

    CreditCardSnPrefix(String type) {
      this.type = type;
    }
  }


}
