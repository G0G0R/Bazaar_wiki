package com.main.wiki.item.repository;

import com.main.common.util.Tier;
import com.main.wiki.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {

    List<Item> findByTier(Tier tier);

    @Query("SELECT i FROM Item i WHERE :hero MEMBER OF i.heroes")
    List<Item> findByHero(String hero);

    @Query("SELECT i FROM Item i WHERE i.tier = :tier AND :hero MEMBER OF i.heroes")
    List<Item> findByTierAndHero(Tier tier, String hero);
}