package com.practis.rest.dto.company.library;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
    @JsonInclude(Include.NON_EMPTY)
    Integer minRepsCount;
  }
}


