package com.hellheim.engine.item.instance;

import com.hellheim.engine.item.template.CardTemplate;
import com.hellheim.engine.item.template.EquipmentTemplate;

import java.util.*;

/**
 * Representa una instancia única y con historia de un equipo.
 * <p>
 * DISEÑO: Es una ENTIDAD (OOP).
 * Mantiene su identidad a través de un UUID y permite la mutación interna
 * de su estado (refine, cartas) sin cambiar la referencia del objeto.
 */
public final class EquipInstance implements ItemInstance {

    private final UUID uniqueId;
    private final EquipmentTemplate itemTemplate;
    private final List<CardTemplate> socketedCards;
    private int refineLevel;

    /**
     * Constructor para una nueva instancia de equipo.
     * @param itemTemplate La plantilla base.
     */
    public EquipInstance(EquipmentTemplate itemTemplate) {
        this.uniqueId = UUID.randomUUID();
        this.itemTemplate = Objects.requireNonNull(itemTemplate);
        this.refineLevel = 0;
        // ArrayList mutable porque vamos a añadir cartas a ESTA instancia.
        this.socketedCards = new ArrayList<>(itemTemplate.cardSlots());
    }

    // --- MÉTODOS DE ESTADO (MUTABLES) ---

    public int getRefineLevel() {
        return refineLevel;
    }

    public void setRefineLevel(int refineLevel) {
        this.refineLevel = refineLevel;
    }

    public List<CardTemplate> getSocketedCards() {
        // Devolvemos vista inmutable para evitar modificaciones externas sin pasar por addCard
        return Collections.unmodifiableList(socketedCards);
    }

    public void addCard(CardTemplate card) {
        // Aquí podrías añadir validaciones (si cabe en los slots, etc)
        this.socketedCards.add(card);
    }

    // --- IDENTIDAD Y DATOS ---

    public UUID getUniqueId() {
        return uniqueId;
    }

    public EquipmentTemplate getItemTemplate() {
        return itemTemplate;
    }

    public String getName() {
        return itemTemplate.name();
    }

    @Override
    public long getTemplateId() {
        return itemTemplate.id();
    }

    // --- EQUALS & HASHCODE (SOLO UUID) ---
    // Vital para que funcione correctamente en Sets o Mapas como clave,
    // independientemente de si cambia su refine.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipInstance that = (EquipInstance) o;
        return uniqueId.equals(that.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }
}