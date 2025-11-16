package com.hellheim.engine.actor.combat;

import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * Un record inmutable que representa la posición de un actor en un mapa.
 *
 * @param mapId     El ID del mapa donde se encuentra el actor.
 * @param x         Coordenada X.
 * @param y         Coordenada Y.
 * @param direction Dirección a la que mira el actor (ej. 0-7).
 */
@RecordBuilder
public record Position(
        String mapId,
        int x,
        int y,
        int direction
) implements PositionBuilder.With {

    public static final Position ZERO = new Position("PRT_INIT", 0, 0, 0);

    /**
     * Calcula la distancia (simplificada) a otra posición.
     */
    public int distanceTo(Position other) {
        if (!this.mapId.equals(other.mapId)) {
            return Integer.MAX_VALUE; // No están en el mismo mapa
        }
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return Math.max(dx, dy); // Distancia Chebyshev (coste de movimiento en RO)
    }
}
