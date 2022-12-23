package com.practis.rest.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RestSearchRequest {

    String query;
    // @Builder.Default String[] filters = new String[] {};
    // Map<String, Object> orderBy;
    @Builder.Default Integer limit = 100;
    @Builder.Default Integer offset = 0;
    @Builder.Default String sort = "updated_at_desc";
    // @Builder.Default Integer totalCount = 0;
    // @Builder.Default Integer numberOfPages = 0;
}
