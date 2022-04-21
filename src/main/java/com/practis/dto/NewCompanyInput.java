package com.practis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class NewCompanyInput {

  String name;
  String email;
  String firstName;
  String lastName;
}
