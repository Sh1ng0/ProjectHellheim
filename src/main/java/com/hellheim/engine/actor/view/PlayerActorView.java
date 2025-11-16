package com.hellheim.engine.actor.view;

import com.hellheim.engine.actor.PlayerActorViewBuilder;
import com.hellheim.engine.actor.combat.Position;
import com.hellheim.engine.actor.player.PlayerData;
import com.hellheim.engine.actor.profile.BaseProfile;
import io.soabase.recordbuilder.core.RecordBuilder;
import java.util.UUID;
// import java.util.Map;
// import com.hellheim.engine.actor.combat.StatusEffect;

/**
 * Representa el estado "caliente" (Hot Data) de un jugador en el mundo.
 * Es la "foto" inmutable del jugador en un tick específico del juego.
 *
 * Implementa la interfaz {@link ActorView} y añade las referencias
 * específicas del jugador a sus datos fuente y calculados.
 */
@RecordBuilder
public record PlayerActorView(
        // --- Campos de la interfaz ActorView ---
        UUID uniqueId,
        Position position,
        int currentHp,
        int currentSp,
        BaseProfile profile,

        // --- Campos específicos del Jugador ---

        /**
         * Los datos "fuente" del jugador (equipo, stats base, inventario).
         * La "entrada" para los cálculos.
         */
        PlayerData sourceData,

        /**
         * Controla cuándo el jugador puede volver a actuar (atacar, usar skill).
         * Se basa en el ASPD (profile.attackDelayInTicks).
         */
        long nextActionTick

        // Map<StatusEffect, Long> activeStatusEffects // (Lo añadiremos)

) implements ActorView, PlayerActorViewBuilder.With {

    /**
     * Constructor compacto para validar y "sujetar" (clamp) los valores
     * de HP y SP, asegurando que nunca excedan los máximos del perfil.
     */
    public PlayerActorView {
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