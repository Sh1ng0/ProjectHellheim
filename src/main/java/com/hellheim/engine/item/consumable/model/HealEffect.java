package com.hellheim.engine.item.consumable.model;

import com.hellheim.engine.enums.Resource;

/**
 * Un efecto que restaura (o daña) un recurso específico por una cantidad fija.
 * <p>
 * Ejemplo: {@code new HealEffect(Resource.HP, 175)} para una Poción Amarilla.
 *
 * @param resource El recurso a modificar (HP, SP).
 * @param amount La cantidad a restaurar. Puede ser negativo para efectos de daño/drenaje.
 */
public record HealEffect(Resource resource, int amount) implements ConsumableEffect {
}