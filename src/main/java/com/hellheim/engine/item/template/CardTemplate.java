package com.hellheim.engine.item.template;

import com.hellheim.engine.item.ItemCategory;
import com.hellheim.engine.item.card.model.CardEffect;
import com.hellheim.engine.item.card.model.CardSocketType;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

/**
 * Blueprint inmutable para una carta (Card).
 * Define sus datos base y la lista de efectos que aplica al ser equipada.
 */
@RecordBuilder
public record CardTemplate(
        long id,
        String name,
        List<CardEffect> effects,
        CardSocketType socketType
) implements ItemTemplate, CardTemplateBuilder.With {

    @Override
    public ItemCategory getCategory() {
        return ItemCategory.CARD;
    }
}