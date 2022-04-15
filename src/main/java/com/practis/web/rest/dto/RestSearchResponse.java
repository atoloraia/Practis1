package com.practis.web.rest.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RestSearchResponse<T> {

  List<T> items;
}
