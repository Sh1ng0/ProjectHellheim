package com.hellheim.engine.item.template;

import com.hellheim.engine.enums.Element;
import com.hellheim.engine.item.ItemCategory;
import com.hellheim.engine.item.equip.model.EquipmentBonuses;
import com.hellheim.engine.item.equip.model.EquipmentType;
import com.hellheim.engine.item.equip.model.WeaponType;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Collections;
import java.util.List;

import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * Blueprint inmutable para un arma.
 * Incluye lógica específica para dual-wielding a través de 'compatibleOffHandTypes'.
 */
@RecordBuilder
public record WeaponTemplate(
        long id,
        String name,
        Element element,
        int weaponLevel,
        WeaponType type,
        double speedRatio,  // Modificador base para la ASPD
        EquipmentBonuses bonuses,
        int requiredLevel,
        List<String> equippableJobs,
        int cardSlots,

        /**
         * Define qué tipos de equipo pueden llevarse en la mano secundaria
         * si se equipa esta arma en la principal.
         * Vital para la lógica de Assassin / Dual Wield.
         */
        List<EquipmentType> compatibleOffHandTypes

) implements EquipmentTemplate, WeaponTemplateBuilder.With {

    /**
     * Constructor de conveniencia para armas que NO permiten dual-wielding especial.
     * Inicializa 'compatibleOffHandTypes' como lista vacía.
     */
    public WeaponTemplate(long id, String name, Element element, int weaponLevel, WeaponType type, double speedRatio, EquipmentBonuses bonuses,
                          int requiredLevel, List<String> equippableJobs, int cardSlots) {
        this(id, name, element, weaponLevel, type, speedRatio, bonuses, requiredLevel, equippableJobs, cardSlots, Collections.emptyList());
    }

    @Override
    public ItemCategory getCategory() {
        return ItemCategory.EQUIPMENT;
    }
}