package com.hellheim.engine.actor.profile;

import com.hellheim.engine.enums.Element;
import com.hellheim.engine.enums.Race;
import com.hellheim.engine.enums.Size;
import com.hellheim.engine.stat.*;

import java.util.Map;

/**
 * Un 'record' inmutable que representa la "hoja de estadísticas" de un monstruo.
 * <p>
 * Es una implementación directa y simple de {@link BaseProfile},
 * conteniendo solo las estadísticas de combate compartidas.
 * <p>
 * A diferencia de {@code PlayerProfile}, este perfil es <strong>público</strong>.
 * Se trata como un "template" o "blueprint" inmutable que se carga
 * desde una base de datos (ej. a través de un {@code MonsterRepository}).
 * No representa el estado de una instancia viva, sino la definición de un tipo de monstruo.
 */
public record MonsterProfile(
        // Basic Identifiers
        long id,
        String name,
        int baseLevel,
        String jobId, // (Ej. "MONSTER")

        // Combat Resources
        int maxHp,
        int maxSp,

        // Stats and Properties
        StatBlock totalStats, // Las stats finales, ya calculadas
        Race race,
        Size size,
        Element attackElement,
        Element defenseElement,

        // Calculated Combat Attributes
        Attack attack,
        int hitRate,
        int attackDelayInTicks, // (ASPD)
        int criticalRate,
        MagicAttack magicAttack,
        Defense defense,
        MagicDefense magicDefense,
        Flee flee,

        // Skills (Placeholder)
        Map<String, Integer> availableSkills
) implements BaseProfile {
}