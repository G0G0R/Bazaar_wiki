package com.main.wiki.item.repository;

import com.main.wiki.item.model.Effect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EffectRepository extends JpaRepository<Effect, String> {
}
