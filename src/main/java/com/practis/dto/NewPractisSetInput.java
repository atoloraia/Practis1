package com.practis.dto;

import com.practis.rest.dto.company.library.RestPractisSetRequest;
import com.practis.rest.dto.company.library.RestPractisSetRequest.Content;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class NewPractisSetInput {

  Integer id;
  String name;
  String description;
  List<RestPractisSetRequest.Content> content;

  @Value
  @Builder
  public static class Content {
    Integer id;
    String type;
  }

}
