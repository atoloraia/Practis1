package com.practis.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewAdminInput {

  String password;
  String email;
  String firstName;
  String lastName;

}
