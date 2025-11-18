package com.hellheim.engine.item.template;

import com.hellheim.engine.item.ItemCategory;
import com.hellheim.engine.item.consumable.model.ConsumableEffect;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

/**
 * Blueprint inmutable para items consumibles (Pociones, Comida, Pergaminos).
 */
@RecordBuilder
public record ConsumableTemplate(
        long id,
        String name,
        List<ConsumableEffect> effects
) implements ItemTemplate, ConsumableTemplateBuilder.With {

    @Override
    public ItemCategory getCategory() {
        return ItemCategory.CONSUMABLE;
    }
}