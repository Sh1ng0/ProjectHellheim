package com.hellheim.engine.stat;

import com.hellheim.engine.job.Job;

public class StatCalculator {

    // HP / SP

    public static int calculateBaseMaxHp(int baseLevel, int totalVit, Job job){

        double hpBase = job.getBaseHpConstant() + (baseLevel * job.getLevelHpFactor());

        double maxHp = hpBase * (1.0 + totalVit / 100.0);


        return (int) maxHp;
    }

    public static int calculateBaseMaxSp(int baseLevel, int totalInt, Job job) {


        double spBase = job.getBaseSpConstant() + (baseLevel * job.getLevelSpFactor()); //

        // 2. Aplicamos el modificador de INT (el "core feel")
        double maxSp = spBase * (1.0 + totalInt / 100.0);

        return (int) maxSp;
    }

    // ATTACK

    public static int calculateMeleeStatAttack(int totalStr, int totalDex, int totalLuk){

        int strBonus = (int) Math.pow(totalStr / 10,2); // No casting since we don't want linearity
        int strComponent = totalStr + strBonus;

        int dexComponent = totalDex / 5;
        int lukComponent = totalLuk / 5;

        return strComponent + dexComponent + lukComponent;
    }

    public static int calculateRangedAttack(int totalDex, int totalStr, int totalLuk){

        int dexBonus = (int) Math.pow(totalDex / 10,2);
        int dexComponent = totalDex + dexBonus;

        int strComponent = totalStr / 5;
        int lukComponent = totalLuk / 5;

        return dexComponent + lukComponent + lukComponent;
    }

    // DEF (Soft and HJard)

    public static int calculateFlatStatDefense(int totalVit){

        int comp1 = totalVit / 2;

        int comp2_A = (totalVit * 3) / 10;

        long vitSquared = (long) totalVit * totalVit;
        int comp2_B = (int) (vitSquared / 150L) -1;

        int comp2 = Math.max(comp2_A, comp2_B);

        return comp1 + comp2;
    }


    /**
     * Calcula el componente de defensa porcentual (Hard DEF) basado en bonos de equipo.
     * El resultado es el 'percentageReduction' del record Defense.
     * Fórmula: floor( HARD_DEF_A * (1 + HARD_DEF_B / 100 ) )
     */
    public static int calculatePercentageDefense(int additiveBonus, int multiplicativeBonus) {
        double multiplier = 1.0 + (multiplicativeBonus / 100.0);
        double result = additiveBonus * multiplier;
        return (int) result; // El casting a (int) trunca, lo que equivale a Math.floor()
    }


















}
