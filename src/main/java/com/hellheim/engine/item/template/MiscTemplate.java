package com.hellheim.engine.item.template;

import com.hellheim.engine.item.ItemCategory;
import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * Blueprint inmutable para items misceláneos (Loot, Materiales de crafteo).
 * Generalmente no tienen lógica asociada más allá de existir y tener precio/peso.
 */
@RecordBuilder
public record MiscTemplate(
        long id,
        String name
) implements ItemTemplate, MiscTemplateBuilder.With {

    @Override
    public ItemCategory getCategory() {
        return ItemCategory.MISCELLANEOUS;
    }
}