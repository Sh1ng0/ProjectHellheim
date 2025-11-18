package com.hellheim.engine.item.template;



import com.hellheim.engine.enums.Element;
import com.hellheim.engine.item.ItemCategory;
import com.hellheim.engine.item.equip.model.ArmorType;
import com.hellheim.engine.item.equip.model.EquipmentBonuses;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

/**
 * Blueprint inmutable para armaduras y equipo defensivo (Escudos, Sombreros, etc.).
 */
@RecordBuilder
public record ArmorTemplate(
        long id,
        String name,
        Element element,
        ArmorType type,
        EquipmentBonuses bonuses,
        int requiredLevel,
        List<String> equippableJobs,
        int cardSlots
) implements EquipmentTemplate, ArmorTemplateBuilder.With {

    @Override
    public ItemCategory getCategory() {
        return ItemCategory.EQUIPMENT;
    }
}