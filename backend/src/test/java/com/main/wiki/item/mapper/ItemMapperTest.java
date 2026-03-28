package com.main.wiki.item.mapper;

import com.main.common.util.Tier;
import com.main.testutils.ItemJsonBuilder;
import com.main.wiki.item.dto.ItemJson;
import com.main.wiki.item.model.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ItemMapperTest {

    private final ItemMapper mapper = new ItemMapper();

    @Test
    void shouldMapItemJsonToEntity() {
        ItemJson dto =
                ItemJsonBuilder.anItem()
                        .withId("item1")
                        .withTier("BRONZE")
                        .withName("Fire Sword")
                        .withHeroes("Vanessa")
                        .withTags("Weapon")
                        .withHiddenTags("Crit")
                        .build();

        Item item = mapper.dtoToItem(dto);

        assertThat(item).isNotNull();
        assertThat(item.getId()).isEqualTo("item1");
        assertThat(item.getName()).isEqualTo("Fire Sword");
        assertThat(item.getTier()).isEqualTo(Tier.BRONZE);
        assertThat(item.getHeroes()).containsExactly("Vanessa");
        assertThat(item.getTags()).containsExactly("Weapon");
        assertThat(item.getHiddenTags()).containsExactly("Crit");
    }

    @Test
    void shouldReturnNullWhenDtoIsNull() {

        Item item = mapper.dtoToItem(null);

        assertThat(item).isNull();
    }

    @Test
    void shouldReturnNullWhenLocalizationIsMissing() {

        ItemJson dto = new ItemJson();

        Item item = mapper.dtoToItem(dto);

        assertThat(item).isNull();
    }
}