package com.practis.web.mapper;

import static java.util.stream.Collectors.toList;

import com.practis.web.model.NewItemElement;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class NewItemMapper {

  /**
   * Get new item elements.
   */
  public List<NewItemElement> toItem(final List<WebElement> rowElements) {
    return rowElements.stream()
        .map(element ->
            NewItemElement.builder()
                .element(element)
                .name(element.getText())
                .build())
        .collect(toList());
  }
}
