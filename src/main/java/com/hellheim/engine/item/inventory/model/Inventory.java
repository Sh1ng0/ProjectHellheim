package com.hellheim.engine.item.inventory.model;


import com.hellheim.engine.item.instance.ItemInstance;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Map;
import java.util.Optional;

@RecordBuilder
public record Inventory<K, V extends ItemInstance>(
        Map<K, V> items,
        int capacity
) implements InventoryBuilder.With<K, V> {

    // --- UI / VIEW HELPERS ---

    /**
     * Recupera un item basado en su índice visual (orden de inserción).
     * Optimizado para no generar arrays temporales en cada llamada.
     *
     * @param slotIndex El índice 0-based.
     * @return Optional con el item si existe.
     */
    public Optional<V> getFromSlot(int slotIndex) {
        if (slotIndex < 0 || slotIndex >= items.size()) {
            return Optional.empty();
        }

        // Optimización: Iteramos hasta el índice en lugar de copiar todo el mapa a una lista.
        // En mapas pequeños (inventarios de 100 slots) esto es muy rápido y no genera basura (garbage).
        var iterator = items.values().iterator();
        int currentIndex = 0;

        while (iterator.hasNext()) {
            V item = iterator.next();
            if (currentIndex == slotIndex) {
                return Optional.of(item);
            }
            currentIndex++;
        }

        return Optional.empty();
    }



}



