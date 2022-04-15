package com.practis.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class NewAdminInput {

  String password;
  String email;
  String firstName;
  String lastName;

}
