package com.practis.web.model.grid;

import com.practis.web.model.GridRow;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.openqa.selenium.WebElement;

@Value
@Builder
public class ChallengeGridRow implements GridRow {

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  WebElement rowElement;
  String name;
  String description;
  String status;
  LocalDateTime lastUpdated;
  List<String> labels;
  List<String> customerLines;

}
