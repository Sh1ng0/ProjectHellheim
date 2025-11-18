package com.hellheim.engine.item.template;

import com.hellheim.engine.enums.Element;
import com.hellheim.engine.item.ItemCategory;
import com.hellheim.engine.item.equip.model.EquipmentBonuses;
import com.hellheim.engine.item.equip.model.EquipmentType;

import java.util.List;

/**
 * Interfaz sellada para items equipables (Armas y Armaduras).
 * <p>
 * Añade propiedades de combate y requisitos de uso.
 * Extiende {@link ItemTemplate} y permite solo {@link WeaponTemplate} y {@link ArmorTemplate}.
 */
public sealed interface EquipmentTemplate extends ItemTemplate permits WeaponTemplate, ArmorTemplate {
    // Heredados de ItemTemplate
    long id();
    String name();

    // Propios de equipo
    Element element();
    EquipmentBonuses bonuses();
    int requiredLevel();
    List<String> equippableJobs(); // Usamos String IDs para desacoplar del objeto Job
    int cardSlots();

    /**
     * Tipo general de equipo. Permite categorizar sin necesidad de casting complejo.
     */
    EquipmentType type();

    @Override
    ItemCategory getCategory();
}