package com.hellheim.engine.actor.view;



import com.hellheim.engine.actor.combat.Position;

import com.hellheim.engine.actor.monster.AIState;
import com.hellheim.engine.actor.profile.MonsterProfile;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.UUID;
// import java.util.Map;
// import com.hellheim.engine.actor.combat.StatusEffect;

/**
 * Representa el estado "caliente" (Hot Data) de un monstruo en el mundo.
 * Es la "foto" inmutable del monstruo en un tick específico del juego.
 *
 * Implementa la interfaz {@link ActorView} y añade las referencias
 * específicas del monstruo, como su {@link AIState}.
 */
@RecordBuilder
public record MonsterActorView(
        // --- Campos de la interfaz ActorView ---
        UUID uniqueId,
        Position position,
        int currentHp,
        int currentSp,

        /**
         * Perfil del monstruo (sus stats, raza, etc.).
         * Satisface el método profile() de la interfaz ActorView,
         * ya que MonsterProfile implementa BaseProfile.
         */
        MonsterProfile profile,

        // --- Campos específicos del Monstruo ---

        /**
         * Controla cuándo el monstruo puede volver a actuar (atacar).
         * Se basa en el ASPD (profile.attackDelayInTicks).
         */
        long nextActionTick,

        /**
         * El estado actual de la inteligencia artificial del monstruo.
         */
        AIState aiState

        // Map<StatusEffect, Long> activeStatusEffects // (Lo añadiremos)

) implements ActorView, MonsterActorViewBuilder.With {

    /**
     * Constructor compacto para validar y "sujetar" (clamp) los valores
     * de HP y SP, asegurando que nunca excedan los máximos del perfil.
     */
    public MonsterActorView {
        // Validación de HP
        if (profile != null) { // Evita NullPointer si el profile aún se está construyendo
            if (currentHp > profile.maxHp()) {
                currentHp = profile.maxHp();
            }
            if (currentHp < 0) {
                currentHp = 0;
            }

            // Validación de SP
            if (currentSp > profile.maxSp()) {
                currentSp = profile.maxSp();
            }
            if (currentSp < 0) {
                currentSp = 0;
            }
        }
    }

    /**
     * Método de conveniencia para saber si el actor está vivo.
     */
    public boolean isAlive() {
        return this.currentHp > 0;
    }
}