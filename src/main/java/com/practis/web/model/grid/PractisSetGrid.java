package com.practis.web.model.grid;

import com.practis.web.model.GridRow;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.openqa.selenium.WebElement;

@Value
@Builder
public class PractisSetGrid implements GridRow {

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  WebElement rowElement;
  String title;

}
