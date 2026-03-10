package com.main.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemJson {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("StartingTier")
    private String startingTier;

    @JsonProperty("Localization")
    private LocalizationJson localization;

    private List<String> heroes;

    private List<String> tags;
    private List<String> hiddenTags;
}
