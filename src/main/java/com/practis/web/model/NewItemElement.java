package com.practis.web.model;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import org.openqa.selenium.WebElement;

@Value
@Builder
public class NewItemElement {

  @ToString.Exclude
  WebElement element;
  String name;
}
