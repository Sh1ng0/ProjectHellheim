package com.hellheim.engine.item.equip.model;

import com.hellheim.engine.item.card.model.CardSocketType;

public enum ArmorType implements EquipmentType {
    SHIELD,
    ARMOR,
    HEADGEAR,
    GARMENT,
    FOOTGEAR,
    ACCESSORY;

    @Override
    public CardSocketType getCardSocketType() {
        return switch (this) {
            case SHIELD -> CardSocketType.SHIELD;
            case ARMOR -> CardSocketType.ARMOR;
            case HEADGEAR -> CardSocketType.HEADGEAR;
            case GARMENT -> CardSocketType.GARMENT;
            case FOOTGEAR -> CardSocketType.FOOTGEAR;
            case ACCESSORY -> CardSocketType.ACCESSORY;
        };
    }
}