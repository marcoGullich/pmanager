package com.marcoGullich.pmanager.infrastructure.util;

import java.util.Arrays;
import java.util.List;

public class SortProperties {

    private static final List<String> ALLOWED_PROPERTIES = List.of(
            "title",
            "status",
            "numberOfDays"
    );

    private List<String> sortPropertiesList;

    public SortProperties(String commaSeparatedString) {
        sortPropertiesList = Arrays
                .stream(commaSeparatedString.split(","))
                .filter(ALLOWED_PROPERTIES::contains)
                .toList();
    }

    public List<String> getSortPropertiesList() {
        return sortPropertiesList;
    }

    public void setSortPropertiesList(List<String> sortPropertiesList) {
        this.sortPropertiesList = sortPropertiesList;
    }
}
