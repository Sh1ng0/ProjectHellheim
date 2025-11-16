package com.hellheim.engine.actor.monster;

import java.util.UUID;

/**
 * Un 'record' simple para mantener el estado actual de la IA de un monstruo.
 * Es inmutable.
 *
 * @param targetId El uniqueId del objetivo actual, si lo hay.
 * @param currentBehavior Una cadena que representa el comportamiento (ej. "IDLE", "ATTACKING").
 * @param nextAiTick El tick del juego en el que la IA debe reevaluar su estado.
 */
public record AIState(
        UUID targetId,
        String currentBehavior,
        long nextAiTick
) {
    /** Una constante para un estado de IA inactivo y por defecto. */
    public static final AIState IDLE = new AIState(null, "IDLE", 0);
}