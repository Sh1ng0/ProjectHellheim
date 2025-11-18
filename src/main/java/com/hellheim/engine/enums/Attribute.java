package com.hellheim.engine.enums;


/**
 * Enumeración maestra de todos los atributos modificables numéricamente.
 * <p>
 * Sirve como clave genérica para definir bonos en Cards y Items (Data-Driven),
 * evitando tener que crear clases específicas para "BonoDeFuerza", "BonoDeAgilidad", etc.
 * <p>
 * La traducción de este Enum a los campos reales de {@code EquipmentBonuses}
 * se realiza en la capa de servicio/construcción del perfil.
 */
public enum Attribute {
    // Primary Stats (Base)
    STR, AGI, VIT, INT, DEX, LUK,

    // Secondary Stats (Calculated/Combat)
    MAX_HP, MAX_SP,
    ATK, DEF,
    MATK, MDEF,
    HIT, FLEE, CRIT,
    PERFECT_DODGE,
    ASPD,

    // Percent Stats (Multipliers)
    // Es útil distinguirlos explícitamente para saber a qué campo del builder ir.
    MAX_HP_PERCENT,
    MAX_SP_PERCENT,
    ATK_PERCENT,
    MATK_PERCENT,
    DEF_PERCENT,
    MDEF_PERCENT
}