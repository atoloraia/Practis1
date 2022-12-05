package com.practis.rest.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RestCollection<T> {

    List<T> items;
}
