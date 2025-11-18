package com.hellheim.engine.item.card.model;

import com.hellheim.engine.enums.Attribute;
import com.hellheim.engine.enums.Element;
import com.hellheim.engine.enums.Race;
import com.hellheim.engine.enums.Size;
import com.hellheim.engine.enums.StatusEffect;
import com.hellheim.engine.mechanics.AutocastData;
import com.hellheim.engine.mechanics.Trigger;

/**
 * Interfaz sellada que define el universo de efectos posibles de una carta.
 * <p>
 * Diseño Open/Closed:
 * Podemos extender el sistema añadiendo nuevos records aquí sin tocar la lógica existente,
 * o mejor aún, el compilador nos avisará si añadimos un efecto nuevo y olvidamos tratarlo
 * en el calculador de stats (Exhaustive Pattern Matching).
 */
public sealed interface CardEffect {

    /**
     * Otorga un bono numérico directo a un atributo (Stats primarios o secundarios).
     * Ej: {Attribute.STR, 2} o {Attribute.ATK, 10}.
     */
    record StatBonus(Attribute attribute, int value) implements CardEffect {}

    /**
     * Incrementa el daño porcentual contra una raza específica.
     * Ej: Hydra Card -> {Race.DEMI_HUMAN, 20}.
     */
    record DamageVsRace(Race race, int percent) implements CardEffect {}

    /**
     * Incrementa el daño porcentual contra un tamaño específico.
     * Ej: Skeleton Worker Card -> {Size.MEDIUM, 15}.
     */
    record DamageVsSize(Size size, int percent) implements CardEffect {}

    /**
     * Añade la posibilidad de autolanzar una habilidad bajo ciertas condiciones.
     * Delega la complejidad en el record {@link AutocastData}.
     */
    record Autocast(AutocastData data) implements CardEffect {}

    /**
     * Probabilidad de infligir un estado alterado al enemigo.
     * Ej: Magnolia Card -> {StatusEffect.CURSE, 0.05, Trigger.ON_PHYSICAL_ATTACK}.
     */
    record InflictStatus(StatusEffect status, double chance, Trigger on) implements CardEffect {}

    /**
     * Otorga inmunidad total a un estado alterado.
     * Ej: Marduk Card -> {StatusEffect.SILENCE}.
     */
    record ImmunityToStatus(StatusEffect status) implements CardEffect {}

    /**
     * Cambia el elemento base de la armadura (propiedad defensiva).
     * Ej: Pasana Card -> {Element.FIRE}.
     */
    record EndowArmorWithElement(Element element) implements CardEffect {}

    // Futuras extensiones fáciles:
    // record AddResistance(Element element, int percent) ...
    // record UnbreakableWeapon() ...
}