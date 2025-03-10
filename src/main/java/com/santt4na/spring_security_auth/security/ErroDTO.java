package com.santt4na.spring_security_auth.security;

public class ErroDTO {
  private int status;
  private String menssage;

  public ErroDTO(int status, String menssage) {
    this.status = status;
    this.menssage = menssage;
  }

  public ErroDTO() {
    super();
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMenssage() {
    return menssage;
  }

  public void setMenssage(String menssage) {
    this.menssage = menssage;
  }

}
