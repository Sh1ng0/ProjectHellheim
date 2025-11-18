package com.hellheim.engine.stat;

import com.hellheim.engine.job.Job;


/**
 * Pure static utility class responsible for calculating raw attributes based on stats.
 * <p>
 * Design Philosophy:
 * 1. Stateless: No instance fields. Functions are pure.
 * 2. Integer Arithmetic: Uses integer division to preserve "breakpoints" (RO logic).
 * 3. Source Agnostic: Does not know about Player/Monster objects, only raw values.
 */
public final class StatCalculator {

    private StatCalculator() {
    }


    // ================================================================================
    // HP & SP CALCULATIONS
    // ================================================================================

    /**
     * Calculates the Max HP based on the Job's base/factor and Vitality.
     * <p>
     * Formula: (JobBase + (Level * JobFactor)) * (1 + VIT / 100)
     *
     * @param baseLevel The actor's current base level.
     * @param totalVit  The total Vitality (Base + Bonus).
     * @param job       The Job definition containing HP constants.
     * @return The calculated Max HP.
     */
    public static int calculateBaseMaxHp(int baseLevel, int totalVit, Job job) {

        double hpBase = job.getBaseHpConstant() + (baseLevel * job.getLevelHpFactor());

        double maxHp = hpBase * (1.0 + totalVit / 100.0);


        return (int) maxHp;
    }

    /**
     * Calculates the Max SP based on the Job's base/factor and Intelligence.
     * <p>
     * Formula: (JobBase + (Level * JobFactor)) * (1 + INT / 100)
     *
     * @param baseLevel The actor's current base level.
     * @param totalInt  The total Intelligence (Base + Bonus).
     * @param job       The Job definition containing SP constants.
     * @return The calculated Max SP.
     */
    public static int calculateBaseMaxSp(int baseLevel, int totalInt, Job job) {


        double spBase = job.getBaseSpConstant() + (baseLevel * job.getLevelSpFactor()); //

        double maxSp = spBase * (1.0 + totalInt / 100.0);

        return (int) maxSp;
    }


    // ================================================================================
    // OFFENSIVE STATS (ATK / MATK)
    // ================================================================================

    /**
     * Calculates the Status Attack (Melee) derived from stats.
     * <p>
     * Formula: STR + (STR/10)^2 + DEX/5 + LUK/5
     * Note: Maintains the quadratic STR bonus characteristic of classic mechanics.
     *
     * @param totalStr Total Strength.
     * @param totalDex Total Dexterity.
     * @param totalLuk Total Luck.
     * @return The status physical attack power.
     */
    public static int calculateMeleeStatAttack(int totalStr, int totalDex, int totalLuk) {

        int strBonus = (int) Math.pow(totalStr / 10, 2); // No casting since we don't want linearity
        int strComponent = totalStr + strBonus;

        int dexComponent = totalDex / 5;
        int lukComponent = totalLuk / 5;

        return strComponent + dexComponent + lukComponent;
    }


    /**
     * Calculates the Status Attack (Ranged) derived from stats.
     * <p>
     * Formula: DEX + (DEX/10)^2 + STR/5 + LUK/5
     * Note: Ranged attacks rely primarily on DEX instead of STR.
     *
     * @param totalDex Total Dexterity.
     * @param totalStr Total Strength.
     * @param totalLuk Total Luck.
     * @return The status ranged attack power.
     */
    public static int calculateRangedAttack(int totalDex, int totalStr, int totalLuk) {

        int dexBonus = (int) Math.pow(totalDex / 10, 2);
        int dexComponent = totalDex + dexBonus;

        int strComponent = totalStr / 5;
        int lukComponent = totalLuk / 5;

        return dexComponent + lukComponent + lukComponent;
    }

    /**
     * Calculates the Minimum Magic Attack based on Intelligence.
     * <p>
     * Formula: INT + (INT/7)^2
     *
     * @param totalInt Total Intelligence.
     * @return The minimum status MATK.
     */
    public static int calculateMinStatMagicAttack(int totalInt) {
        int bonusFactor = totalInt / 7;
        return totalInt + (bonusFactor * bonusFactor);
    }

    /**
     * Calculates the Maximum Magic Attack based on Intelligence.
     * <p>
     * Formula: INT + (INT/5)^2
     *
     * @param totalInt Total Intelligence.
     * @return The maximum status MATK.
     */
    public static int calculateMaxStatMagicAttack(int totalInt) {
        int bonusFactor = totalInt / 5;
        return totalInt + (bonusFactor * bonusFactor);
    }


    // ================================================================================
    // DEFENSIVE STATS (DEF / MDEF)
    // ================================================================================

    /**
     * Calculates the "Soft Defense" (Flat Reduction) based purely on Vitality.
     * <p>
     * Formula: floor(VIT / 2) + max( floor(VIT * 0.3), floor(VIT^2 / 150) - 1 )
     *
     * @param totalVit Total Vitality.
     * @return The flat damage reduction value.
     */
    public static int calculateFlatStatDefense(int totalVit) {

        int comp1 = totalVit / 2;

        int comp2_A = (totalVit * 3) / 10;

        long vitSquared = (long) totalVit * totalVit;
        int comp2_B = (int) (vitSquared / 150L) - 1;

        int comp2 = Math.max(comp2_A, comp2_B);

        return comp1 + comp2;
    }


    /**
     * Calculates the "Hard Defense" (Percentage Reduction) derived from Equipment.
     * <p>
     * Formula: floor( AdditiveBonus * (1 + MultiplicativeBonus / 100 ) )
     *
     * @param additiveBonus       Sum of defense from armor/cards.
     * @param multiplicativeBonus Sum of percentage defense buffs.
     * @return The final percentage reduction value (Hard DEF).
     */
    public static int calculatePercentageDefense(int additiveBonus, int multiplicativeBonus) {
        double multiplier = 1.0 + (multiplicativeBonus / 100.0);
        double result = additiveBonus * multiplier;
        return (int) result; // El casting a (int) trunca, lo que equivale a Math.floor()
    }





    // ================================================================================
    // ACCURACY & EVASION (CLASSIC)
    // ================================================================================

    /**
     * Calculates the Hit Rate (Accuracy) based on Classic formulas.
     * <p>
     * Formula: BaseLevel + DEX
     *
     * @param baseLevel The actor's base level.
     * @param totalDex  Total Dexterity.
     * @return The calculated Hit value.
     */
    public static int calculateHit(int baseLevel, int totalDex) {
        return 175 + baseLevel + totalDex;
    }


    /**
     * Calculates the Flee Rate (Evasion) based on Classic formulas.
     * <p>
     * Formula: BaseLevel + AGI
     *
     * @param baseLevel The actor's base level.
     * @param totalAgi  Total Agility.
     * @return The calculated Flee value.
     */
    public static int calculateFlee(int baseLevel, int totalAgi) {
        return baseLevel + totalAgi;
    }


    /**
     * Calculates the Critical Hit Rate.
     * <p>
     * Formula: 1 + (LUK / 3)
     *
     * @param totalLuk Total Luck.
     * @return The critical rate (approximate percentage).
     */
    public static int calculateCrit(int totalLuk) {
        return 1 + (totalLuk / 3);
    }


    /**
     * Calculates Perfect Dodge (Lucky Dodge).
     * <p>
     * Formula: 1 + (LUK / 10)
     *
     * @param totalLuk Total Luck.
     * @return The perfect dodge rate (percentage).
     */
    public static int calculatePerfectDodge(int totalLuk) {
        return 1 + (totalLuk / 10);
    }


}
