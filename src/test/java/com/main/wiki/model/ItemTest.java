package com.main.wiki.model;

import com.main.common.util.Tier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    void shouldCreateItemWithAllArgsConstructor() {
        List<String> heroes = List.of("HeroA");
        List<String> tags = List.of("Economy");
        List<String> hiddenTags = List.of("Hidden");

        Item item = new Item("item-1", "Test item", Tier.GOLD, heroes, tags, hiddenTags);

        assertThat(item.getId()).isEqualTo("item-1");
        assertThat(item.getName()).isEqualTo("Test item");
        assertThat(item.getTier()).isEqualTo(Tier.GOLD);
        assertThat(item.getHeroes()).containsExactly("HeroA");
        assertThat(item.getTags()).containsExactly("Economy");
        assertThat(item.getHiddenTags()).containsExactly("Hidden");
    }

    @Test
    void shouldSetAndGetFieldsWithNoArgsConstructor() {
        Item item = new Item();

        item.setId("item-2");
        item.setName("Another item");
        item.setTier(Tier.SILVER);
        item.setHeroes(List.of("HeroB"));
        item.setTags(List.of("Tempo"));
        item.setHiddenTags(List.of("Secret"));

        assertThat(item.getId()).isEqualTo("item-2");
        assertThat(item.getName()).isEqualTo("Another item");
        assertThat(item.getTier()).isEqualTo(Tier.SILVER);
        assertThat(item.getHeroes()).containsExactly("HeroB");
        assertThat(item.getTags()).containsExactly("Tempo");
        assertThat(item.getHiddenTags()).containsExactly("Secret");
    }

}
