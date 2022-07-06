package com.practis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewUserInput {

  String email;
  String firstName;
  String lastName;
}
