package com.practis.web.page.admin.administrators.mapper;

import com.practis.dto.admin.Administrator;
import com.practis.utils.WebGridUtils;
import java.time.LocalDateTime;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@UtilityClass
public class AdministratorsMapper {

  /**
   * To be added.
   */
  public static Administrator fromGridRow(
      final WebElement row,
      final String columnsSelector) {
    return Administrator.builder()
        .firstName(parseFirstName(row, columnsSelector))
        .lastName(parseLastName(row, columnsSelector))
        .email(parseEmail(row, columnsSelector))
        .dateCreated(parseDateCreated(row, columnsSelector))
        .owner(parseOwner(row, columnsSelector))
        .build();
  }

  public static String firstNameFromString(final String input) {
    return input.split(" ")[0];
  }

  public static String lastNameFromString(final String input) {
    return input.split(" ")[1];
  }

  private static String parseFirstName(final WebElement row, final String columnsSelector) {
    return firstNameFromString(getGridNameValue(getFields(row, columnsSelector)));
  }

  private static String parseLastName(final WebElement row, final String columnsSelector) {
    return lastNameFromString(getGridNameValue(getFields(row, columnsSelector)));
  }

  private static String parseEmail(final WebElement row, final String columnsSelector) {
    return getFields(row, columnsSelector).get(1).getText();
  }

  private static LocalDateTime parseDateCreated(final WebElement row,
      final String columnsSelector) {
    return WebGridUtils.parseDate(getFields(row, columnsSelector).get(2).getText());
  }

  private static String parseOwner(final WebElement row, final String columnsSelector) {
    return getFields(row, columnsSelector).get(3).getText();
  }

  private static String getGridNameValue(final List<WebElement> columns) {
    return columns.get(0).getText().split("\n")[1];
  }

  private static List<WebElement> getFields(final WebElement row, final String columnsSelector) {
    return row.findElements(By.cssSelector(columnsSelector));
  }
}
