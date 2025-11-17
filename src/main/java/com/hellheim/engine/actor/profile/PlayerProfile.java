package com.hellheim.engine.actor.profile;

import com.hellheim.engine.Stance.Stance;
import com.hellheim.engine.actor.PlayerProfileBuilder;
import io.soabase.recordbuilder.core.RecordBuilder;

import com.hellheim.engine.enums.Element;
import com.hellheim.engine.enums.Race;
import com.hellheim.engine.enums.Size;
import com.hellheim.engine.stat.*;

import java.util.Map;


/**
 * Implementación de "stat sheet" para un jugador.
 * Contiene todos los datos "fríos" (calculados) de un jugador en un momento dado.
 */
@RecordBuilder
public record PlayerProfile(


        // Basic Identifiers
        long id,
        String name,
        int baseLevel,
        int jobLevel,
        String jobId, // Pensar en el enum quizá para jobs

        // Combat Resources
        int maxHp,
        int maxSp,

        // Stats and Properties
        StatBlock totalStats,
        Race race,
        Size size,
        Element attackElement,
        Element defenseElement,

        // Calculated Combat Attributes
        Attack attack,
        int hitRate,
        int attackDelayInTicks,
        int criticalRate,
        MagicAttack magicAttack,
        Defense defense,
        MagicDefense magicDefense,
        Flee flee,

        // Skills
        Map<String, Integer> availableSkills,

        // Stance
        Stance stance

) implements BaseProfile, PlayerProfileBuilder.With {
    // Nota: Añadimos "PlayerProfileBuilder.With" para que el método with()
    // devuelva nuestra implementación concreta y no la interfaz.
    // Esto es estándar en RecordBuilder.
}