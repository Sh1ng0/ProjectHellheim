package com.hellheim.engine.stat;

import io.soabase.recordbuilder.core.RecordBuilder;


import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * Represents an immutable block of primary character statistics (stats).
 * This record is annotated with {@link RecordBuilder} to generate a companion builder class.
 *
 * @param str   Strength
 * @param agi   Agility
 * @param vit   Vitality
 * @param intel Intelligence
 * @param dex   Dexterity
 * @param luk   Luck
 */
@RecordBuilder
public record StatBlock(
        int str,
        int agi,
        int vit,
        int intel,
        int dex,
        int luk
) {

    /**
     * A constant representing a {@code StatBlock} with all stats set to zero.
     * Useful for initialization or as a neutral element in additions.
     */
    public static final StatBlock ZERO = new StatBlock(0, 0, 0, 0, 0, 0);

    /**
     * Compact constructor to validate that base stats are not negative.
     * This validation is enforced every time a {@code StatBlock} is instantiated.
     *
     * @throws IllegalArgumentException if any stat value is negative.
     */
    public StatBlock {
        if (str < 0 || agi < 0 || vit < 0 || intel < 0 || dex < 0 || luk < 0) {
            throw new IllegalArgumentException("Base stats cannot be negative");
        }
    }

    /**
     * Adds another {@code StatBlock} to this one and returns a new {@code StatBlock}
     * with the combined values.
     * <p>
     * This operation is purely additive and does not mutate the current instance.
     *
     * @param other The {@code StatBlock} to add to this one. Must not be null.
     * @return A new {@code StatBlock} instance representing the sum of both blocks.
     */
    public StatBlock add(StatBlock other) {
        return new StatBlock(
                this.str + other.str,
                this.agi + other.agi,
                this.vit + other.vit,
                this.intel + other.intel,
                this.dex + other.dex,
                this.luk + other.luk
        );
    }
}