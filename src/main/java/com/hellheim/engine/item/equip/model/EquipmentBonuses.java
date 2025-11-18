package com.hellheim.engine.item.equip.model;

import com.hellheim.engine.mechanics.AutocastData;
import com.hellheim.engine.stat.StatBlock;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;




/**
 * Record inmutable que agrupa TODOS los bonos posibles que un equipo o carta puede otorgar.
 * <p>
 * Se usa una estructura "aplanada" y exhaustiva para simplificar el cálculo de stats:
 * en lugar de tener objetos complejos jerárquicos, tenemos los valores crudos listos para sumar.
 */
@RecordBuilder
public record EquipmentBonuses(
        // Bonus a stats primarios (STR, AGI, etc.)
        StatBlock primaryStats,

        // Bonus planos (Flat) a stats secundarios
        int maxHp,
        int maxSp,
        int attack,
        int defense,
        int magicAttack,
        int magicDefense,
        int criticalRate,
        int flee,
        int hit,

        // Bonus porcentuales (Multipliers)
        double maxHpPercent,
        double maxSpPercent,
        double attackPercent,

        // Efectos especiales y habilidades
        Map<String, Integer> grantSkills,
        List<AutocastData> autocastEffects

) implements EquipmentBonusesBuilder.With {

    /**
     * Constante "Null Object" para representar ausencia de bonos sin usar null.
     */
    public static final EquipmentBonuses ZERO = new EquipmentBonuses(
            StatBlock.ZERO,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0.0, 0.0, 0.0,
            Collections.emptyMap(), Collections.emptyList()
    );

    /**
     * Combina (suma) este set de bonos con otro.
     * Vital para sumar los bonos de todo el equipo equipado (Casco + Armadura + Botas...).
     *
     * @param other El otro set de bonos a sumar.
     * @return Una NUEVA instancia con la suma de ambos.
     */
    public EquipmentBonuses add(EquipmentBonuses other) {
        if (other == null || other == ZERO) {
            return this;
        }

        // Nota: Usamos el constructor canónico, pero podríamos usar el builder si la lógica se complica.
        // Por ahora, la suma directa es más eficiente (performance-critical code).
        return new EquipmentBonuses(
                this.primaryStats.add(other.primaryStats),
                this.maxHp + other.maxHp,
                this.maxSp + other.maxSp,
                this.attack + other.attack,
                this.defense + other.defense,
                this.magicAttack + other.magicAttack,
                this.magicDefense + other.magicDefense,
                this.criticalRate + other.criticalRate,
                this.flee + other.flee,
                this.hit + other.hit,
                this.maxHpPercent + other.maxHpPercent,
                this.maxSpPercent + other.maxSpPercent,
                this.attackPercent + other.attackPercent,

                // TODO: Implementar fusión inteligente de mapas y listas cuando tengamos el sistema de Skills.
                // De momento, prevalece el mapa original (this) para evitar complejidad prematura.
                this.grantSkills,
                this.autocastEffects
        );
    }


}