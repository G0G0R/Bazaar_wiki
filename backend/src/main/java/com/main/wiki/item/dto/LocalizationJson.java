package com.main.wiki.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalizationJson {

    @JsonProperty("Title")
    private TitleJson title;

    @JsonProperty("Tooltips")
    private TooltipsJson[] tooltipsList;

    public TitleJson getTitle() {
        return title;
    }

    public void setTitle(TitleJson title) {
        this.title = title;
    }

    public TooltipsJson[] getTooltipsList() {
        return tooltipsList;
    }

    public void setTooltipsList(TooltipsJson[] tooltipsList) {
        this.tooltipsList = tooltipsList;
    }
}
