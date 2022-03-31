package com.practis.web.page.company.library.tab.challenges.mapper;

import static java.util.Collections.emptyList;
import static org.openqa.selenium.By.cssSelector;

import com.practis.configuration.selenium.element.EmptyElement;
import com.practis.dto.practis.Challenge;
import com.practis.utils.WebGridUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@UtilityClass
public class ChallengeMapper {

  /**
   * To be added.
   */
  public static Challenge fromGridRow(
      final WebElement row,
      final String columnsSelector,
      final WebElement labelsGridRowElement) {
    return Challenge.builder()
        .name(parseName(row, columnsSelector))
        .lastUpdated(parseLastUpdated(row, columnsSelector))
        .labels(parseLabels(row, labelsGridRowElement, columnsSelector))
        .build();
  }

  public static String titleFromGridRow(
      final WebElement row,
      final String columnsSelector) {
    return parseName(row, columnsSelector);
  }

  private static List<String> parseLabels(
      final WebElement row, final WebElement labelsRow, final String columnsSelector) {
    getFields(row, columnsSelector).get(3).click();
    if (labelsRow.getTagName().equals("empty")) {
      return emptyList();
    }
    return labelsRow.findElements(By.xpath(".//td")).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  private static String parseName(final WebElement row, final String columnsSelector) {
    return getFields(row, columnsSelector).get(1).getText();
  }

  private static LocalDateTime parseLastUpdated(final WebElement row,
      final String columnsSelector) {
    return WebGridUtils.parseDate(getFields(row, columnsSelector).get(2).getText());
  }

  private static List<WebElement> getFields(final WebElement row, final String columnsSelector) {
    return row.findElements(cssSelector(columnsSelector));
  }
}
