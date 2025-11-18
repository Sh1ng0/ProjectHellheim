package com.hellheim.engine.item.equip.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hellheim.engine.item.card.model.CardSocketType;

/**
 * Interfaz común para tipos de equipo.
 * <p>
 * Incluye un Factory Method (@JsonCreator) que permite a Jackson deserializar
 * strings planos (ej. "DAGGER", "SHIELD") directamente al Enum correcto,
 * buscando primero en armas y luego en armaduras.
 */
public sealed interface EquipmentType permits WeaponType, ArmorType {

    CardSocketType getCardSocketType();

    /**
     * Método factoría mágico para Jackson.
     * Unifica los dos Enums (WeaponType y ArmorType) bajo un mismo paraguas de deserialización.
     *
     * @param type El string crudo del JSON.
     * @return El enum concreto correspondiente.
     */
    @JsonCreator
    static EquipmentType fromString(String type) {
        try {
            // 1. Intentamos parsearlo como Arma
            return WeaponType.valueOf(type);
        } catch (IllegalArgumentException e) {
            // 2. Si falla, asumimos que es Armadura (o explotará aquí si no existe)
            return ArmorType.valueOf(type);
        }
    }
}