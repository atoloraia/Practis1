package com.practis.dto.admin;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class Administrator {

  String firstName;
  String lastName;
  String owner;
  LocalDateTime dateCreated;
  String email;
}
