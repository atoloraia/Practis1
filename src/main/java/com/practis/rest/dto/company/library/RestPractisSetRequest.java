package com.practis.rest.dto.company.library;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestPractisSetRequest {

  String name;
  String description;
  @Builder.Default
  String status = "ACTIVE";
  Integer pacingId = 1;
  List<Content> content;

  @Value
  @Builder
  public static class Content {
    Integer id;
    String type;
  }
}


