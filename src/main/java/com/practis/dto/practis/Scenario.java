package com.practis.dto.practis;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Scenario {

  String title;
  String description;
  Integer customerLinesCount;
  Integer repLinesCount;
  Integer playButtonsCount;
  String totalDuration;
  List<String> customerLines;
  List<String> repLines;

}
