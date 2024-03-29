package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Grid {

    private final ElementsCollection tableRows = $$("tbody tr");

    /** Looking for a row matching text in grid. */
    public GridRow getRow(final String value) {
        final var row = tableRows.find(matchText(value));
        return new GridRow(row);
    }

    public List<GridRow> getRows() {
        final var result = new ArrayList<GridRow>();
        for (var tableRow : tableRows) {
            result.add(new GridRow(tableRow));
        }
        return result;
    }
}
