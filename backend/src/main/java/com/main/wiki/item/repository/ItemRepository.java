package com.main.wiki.item.repository;

import com.main.common.util.Tier;
import com.main.common.util.Size;
import com.main.wiki.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {

    @Query("SELECT i FROM Item i WHERE :hero MEMBER OF i.heroes")
    List<Item> findByHero(String hero);

    @Query("SELECT i FROM Item i WHERE i.tier = :tier")
    List<Item> findByTier(Tier tier);

    @Query("SELECT i FROM Item i WHERE i.size = :size")
    List<Item> findBySize(Size size);

    @Query("""
    SELECT i
    FROM Item i
    LEFT JOIN i.tags t
    LEFT JOIN i.hiddenTags ht
    WHERE t IN :tags OR ht IN :tags
    GROUP BY i
    HAVING COUNT(DISTINCT CASE
        WHEN t IN :tags THEN t
        WHEN ht IN :tags THEN ht
    END) = :#{#tags.size()}
""")
    List<Item> findByTags(List<String> tags);
}