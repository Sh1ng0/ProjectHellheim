package com.hellheim.engine.actor.view;

import com.hellheim.engine.actor.combat.Position;
import com.hellheim.engine.actor.profile.BaseProfile;

import java.util.UUID;

/**
 * Una interfaz sellada que representa los "datos calientes" o la "cáscara viva"
 * de cualquier actor actualmente activo en el mundo del juego (en un mapa).
 * <p>
 * Este contrato define el conjunto mínimo de datos que cambian rápidamente
 * (como la posición y el HP actual) que el motor del juego (por ejemplo,
 * el gestor de mapas, el sistema de movimiento) necesita para rastrear a un actor.
 *
 * @see BaseProfile para los "datos fríos" o "calculados" (la ficha de estadísticas).
 */
public sealed interface ActorView permits PlayerActorView, MonsterActorView{


    UUID uniqueId();

    Position position();

    int currentHp();

    int currentSp();

    /**
     * Proporciona acceso a la "ficha de estadísticas" del actor.
     * <p>
     * Este es el enlace a todos los datos de combate "fríos" y calculados
     * (ATK, DEF, MaxHP, Stats, etc.).
     *
     * @return La implementación de BaseProfile del actor (PlayerProfile o MonsterProfile).
     */
    BaseProfile profile();
}