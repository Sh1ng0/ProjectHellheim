package com.hellheim.engine.item.instance;

import com.hellheim.engine.item.template.ItemTemplate;
import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * Record inmutable para items apilables.
 * DISEÑO: Es un VALUE OBJECT (DOP).
 */
@RecordBuilder
public record ItemStack(
        ItemTemplate template,
        int quantity
) implements ItemInstance, ItemStackBuilder.With {

    @Override
    public long getTemplateId() {
        return template.id();
    }

    /**
     * Retorna un NUEVO stack con la cantidad modificada.
     */
    public ItemStack withQuantityChangedBy(int amount) {
        return this.withQuantity(Math.max(0, this.quantity + amount));
    }
}