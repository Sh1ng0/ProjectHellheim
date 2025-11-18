package com.hellheim.engine.item.template;

import com.hellheim.engine.item.ItemCategory;

/**
 * La interfaz raíz sellada (sealed) para todos los "blueprints" de items.
 * <p>
 * Define el contrato mínimo (ID, nombre, categoría) y restringe la jerarquía
 * a los tipos conocidos para permitir un pattern matching exhaustivo.
 */
public sealed interface ItemTemplate permits EquipmentTemplate, ConsumableTemplate, MiscTemplate, CardTemplate {
    long id();
    String name();
    ItemCategory getCategory();
}