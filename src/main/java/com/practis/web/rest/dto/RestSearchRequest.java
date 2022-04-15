package com.practis.web.rest.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RestSearchRequest {

  String searchTerm;
  @Builder.Default
  String[] filters = new String[] {};
  Map<String, Object> orderBy;
  @Builder.Default
  Integer limit = 20;
  @Builder.Default
  Integer offset = 0;
  @Builder.Default
  Integer totalCount = 0;
  @Builder.Default
  Integer numberOfPages = 0;
}
