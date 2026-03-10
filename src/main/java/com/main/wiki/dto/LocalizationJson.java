package com.main.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizationJson {

    @JsonProperty("Title")
    private TitleJson title;
}
