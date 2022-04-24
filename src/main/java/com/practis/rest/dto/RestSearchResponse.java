package com.practis.rest.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RestSearchResponse<T> {

  List<T> items;
}
