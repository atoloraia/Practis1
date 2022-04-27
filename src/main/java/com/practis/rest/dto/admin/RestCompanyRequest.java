package com.practis.rest.dto.admin;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCompanyRequest {
  String name;
  String ownerEmail;
  String ownerFirstName;
  String ownerLastName;

}
