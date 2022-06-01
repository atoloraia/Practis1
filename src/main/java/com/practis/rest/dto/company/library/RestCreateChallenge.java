package com.practis.rest.dto.company.library;

import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCreateChallenge {

  Challenge challenge;
  List<Line> lines;

  @Value
  @Builder
  public static class Line {
    @Builder.Default
    BigDecimal duration = ONE;
    @Builder.Default
    Integer position = 1;
    @Builder.Default
    String speaker = "CUSTOMER";
    String text;
  }

  @Value
  @Builder
  public static class Challenge {
    String description;
    String instructions;
    @Builder.Default
    String status = "ACTIVE";
    String title;
  }
}
