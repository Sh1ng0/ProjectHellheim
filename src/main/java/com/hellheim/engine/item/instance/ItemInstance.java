package com.hellheim.engine.item.instance;

/**
 * Interfaz sellada que engloba cualquier objeto que pueda residir en un inventario.
 * <p>
 * Permite un modelo híbrido:
 * - {@link EquipInstance} (Clase): Entidades únicas mutables (Armas, Armaduras).
 * - {@link ItemStack} (Record): Value Objects inmutables (Pociones, Items varios).
 */
public sealed interface ItemInstance permits EquipInstance, ItemStack {

    /**
     * Devuelve el ID del template base.
     * Común para todo item, sea único o stack.
     */
    long getTemplateId();
}