package com.practis.configuration.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Data
public class Credentials {

  private String email;
  private String password;
}
