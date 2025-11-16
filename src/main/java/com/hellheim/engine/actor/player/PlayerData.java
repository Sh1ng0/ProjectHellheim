package com.hellheim.engine.actor.player;

import com.hellheim.engine.stat.StatBlock;
// import com.hellheim.engine.character.CharacterEquipment; // Aún no existe
// import com.hellheim.engine.item.inventory.model.CharacterInventories; // Aún no existe
import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * Representa los "Datos Fuente" (Source Data) inmutables de un jugador.
 *
 * Este record contiene la información "raíz" que define a un jugador
 * (stats base asignados, equipo, etc.) y sirve como la entrada principal
 * para el StatCalculatorService.
 */
@RecordBuilder
public record PlayerData(
        long playerId,
        int baseLevel,
        int jobLevel,
        String jobId,
        StatBlock baseStats // Los stats asignados por el jugador

        // --- Campos que añadiremos cuando existan las clases ---
        // CharacterEquipment equipment,
        // CharacterInventories inventories,
        // List<ActiveSkill> learnedSkills

) implements PlayerDataBuilder.With {

    /**
     * Constante para un jugador recién creado.
     */
    public static final PlayerData NEWBIE = new PlayerData(
            0L, 1, 1, "NOVICE", StatBlock.ZERO
            // , CharacterEquipment.UNEQUIPPED, CharacterInventories.EMPTY
    );
}