package com.hellheim.engine.enums;

/**
 * Representa los "recursos de combate" consumibles y regenerables de una entidad.
 * <p>
 * Se distingue de {@link Attribute} en que estos son valores actuales/volátiles (Current HP),
 * mientras que los atributos definen los máximos (Max HP).
 */
public enum Resource {
    HP,
    SP
}