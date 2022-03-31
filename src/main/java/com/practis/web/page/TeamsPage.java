package com.practis.web.page;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.web.component.NavigationComponent;
import com.practis.web.component.NewItemComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@PractisPage
@Getter
@RequiredArgsConstructor
public class TeamsPage {

  private final NewItemComponent newItem;
  private final NavigationComponent navigation;

  public void addNew(final String itemName) {
    newItem.clickNewItem().clickRow(itemName);
  }
}
