package com.main.wiki.mapper;

import com.main.common.util.Tier;
import com.main.wiki.dto.ItemJson;
import com.main.wiki.dto.LocalizationJson;
import com.main.wiki.dto.TitleJson;
import com.main.wiki.model.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemMapperTest {

    private final ItemMapper itemMapper = new ItemMapper();

    @Test
    void shouldReturnNullWhenDtoIsNull() {
        Item item = itemMapper.toEntity(null);

        assertThat(item).isNull();
    }

    @Test
    void shouldReturnNullWhenLocalizationOrTitleIsMissing() {
        ItemJson withoutLocalization = new ItemJson();

        ItemJson withoutTitle = new ItemJson();
        withoutTitle.setLocalization(new LocalizationJson());

        assertThat(itemMapper.toEntity(withoutLocalization)).isNull();
        assertThat(itemMapper.toEntity(withoutTitle)).isNull();
    }

    @Test
    void shouldMapAllSupportedFieldsToEntity() {
        ItemJson dto = new ItemJson();
        dto.setId("item-1");
        dto.setStartingTier("gold");
        dto.setHeroes(List.of("HeroA"));
        dto.setTags(List.of("Economy"));
        dto.setHiddenTags(List.of("Hidden"));

        TitleJson title = new TitleJson();
        title.setText("Golden Item");
        LocalizationJson localization = new LocalizationJson();
        localization.setTitle(title);
        dto.setLocalization(localization);

        Item item = itemMapper.toEntity(dto);

        assertThat(item).isNotNull();
        assertThat(item.getId()).isEqualTo("item-1");
        assertThat(item.getName()).isEqualTo("Golden Item");
        assertThat(item.getTier()).isEqualTo(Tier.GOLD);
        assertThat(item.getHeroes()).containsExactly("HeroA");
        assertThat(item.getTags()).containsExactly("Economy");
        assertThat(item.getHiddenTags()).containsExactly("Hidden");
    }

    @Test
    void shouldMapNullTierWhenStartingTierIsNull() {
        ItemJson dto = new ItemJson();
        dto.setStartingTier(null);

        TitleJson title = new TitleJson();
        title.setText("No Tier Item");
        LocalizationJson localization = new LocalizationJson();
        localization.setTitle(title);
        dto.setLocalization(localization);

        Item item = itemMapper.toEntity(dto);

        assertThat(item).isNotNull();
        assertThat(item.getTier()).isNull();
    }

}
