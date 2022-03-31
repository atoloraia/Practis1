package com.practis.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewCompanyInput {

  String name;
  String email;
  String firstName;
  String lastName;
}
