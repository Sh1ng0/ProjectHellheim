package com.hellheim.engine.item.inventory.model;

import com.hellheim.engine.item.ItemCategory;
import com.hellheim.engine.item.instance.EquipInstance;
import com.hellheim.engine.item.instance.ItemInstance;
import com.hellheim.engine.item.instance.ItemStack;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

/**
 * Contenedor inmutable para todos los inventarios especializados de un personaje.
 * <p>
 * Utiliza @RecordBuilder para facilitar la "modificación" del contenedor
 * (ej. reemplazar el inventario de consumibles entero tras una operación de ordenado).
 */
@RecordBuilder
public record CharacterInventories(
        Inventory<UUID, EquipInstance> equipment,
        Inventory<Long, ItemStack> consumables,
        Inventory<Long, ItemStack> cards,
        Inventory<Long, ItemStack> miscellaneous
) implements CharacterInventoriesBuilder.With {

    /**
     * Constante estática que representa un set de inventarios completamente vacíos.
     * Útil para la inicialización de nuevos personajes (PlayerData.NEWBIE).
     */
    public static final CharacterInventories EMPTY = new CharacterInventories(
            new Inventory<>(Collections.emptyMap(), 100),
            new Inventory<>(Collections.emptyMap(), 100),
            new Inventory<>(Collections.emptyMap(), 100),
            new Inventory<>(Collections.emptyMap(), 100)
    );

    // --- UI / VIEW HELPERS ---

    /**
     * Encuentra un item en una categoría específica por su índice visual de slot.
     * Actúa como traductor entre la vista de rejilla (UI) y el mapa subyacente.
     *
     * @param category  La categoría del inventario a buscar (ej. CONSUMABLE).
     * @param slotIndex El índice (0-based) del slot.
     * @return Un Optional con el ItemInstance si existe.
     */
    public Optional<ItemInstance> getItemFromSlot(ItemCategory category, int slotIndex) {
        // 1. Seleccionamos el inventario correcto
        // 2. Buscamos por slot
        // 3. Hacemos cast seguro a la interfaz común ItemInstance
        return getInventoryByType(category)
                .getFromSlot(slotIndex)
                .map(item -> (ItemInstance) item);
    }

    /**
     * Helper interno para obtener el inventario específico basado en la categoría.
     * Retorna un tipo wildcard acotado porque las claves varían (UUID vs Long).
     */
    private Inventory<?, ? extends ItemInstance> getInventoryByType(ItemCategory category) {
        return switch (category) {
            case EQUIPMENT -> equipment;
            case CONSUMABLE -> consumables;
            case CARD -> cards;
            case MISCELLANEOUS -> miscellaneous;
        };
    }

}