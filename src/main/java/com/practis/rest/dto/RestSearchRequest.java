package com.practis.rest.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RestSearchRequest {

    String searchTerm;
    // @Builder.Default String[] filters = new String[] {};
    @Builder.Default Map<String, Object> orderBy = Map.of("field", "id", "asc", true);
    @Builder.Default Integer limit = 100;
    @Builder.Default Integer offset = 0;
    @Builder.Default List<Object> filters = List.of();
    // @Builder.Default String sort = "updated_at_desc";
    @Builder.Default Integer totalCount = 0;
    @Builder.Default Integer numberOfPages = 0;
}
