package com.hellheim.engine.item.consumable.model;

/**
 * Interfaz sellada que engloba todos los efectos posibles que un consumible
 * puede aplicar al ser usado.
 * <p>
 * Permite un manejo polimórfico seguro en el servicio de items.
 */
public sealed interface ConsumableEffect permits HealEffect {
    // En el futuro aquí irán:
    // permits HealEffect, BuffStatEffect, LearnSkillEffect, etc.
}