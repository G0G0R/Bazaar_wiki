package com.main.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TitleJson {

    @JsonProperty("Text")
    private String text;
}
