package com.hellheim.engine.actor.profile;




import com.hellheim.engine.enums.Element;
import com.hellheim.engine.enums.Race;
import com.hellheim.engine.enums.Size;
import com.hellheim.engine.stat.*;

import java.util.Map;

/**
 * A sealed interface representing the "cold data" or "stat sheet" for any actor in the game.
 * <p>
 * It contains all the slow-changing, calculated combat statistics shared by both players and monsters.
 * This contract ensures that any actor can be handled by systems like combat logic
 * without needing to know if it's a player or a monster.
 */
public sealed interface BaseProfile permits PlayerProfile, MonsterProfile {
    // Basic Identifiers
    long id();

    String name();

    int baseLevel();

//    String jobId();

    // Combat Resources
    int maxHp();

    int maxSp();

    // Stats and Properties
    StatBlock totalStats();

    Race race();

    Size size();

    Element attackElement();

    Element defenseElement();

    // Calculated Combat Attributes
    Attack attack();

    int hitRate();

    int attackDelayInTicks();

    int criticalRate();

    MagicAttack magicAttack();

    Defense defense();

    MagicDefense magicDefense();

    Flee flee();

    // Skills
    Map<String, Integer> availableSkills();
}