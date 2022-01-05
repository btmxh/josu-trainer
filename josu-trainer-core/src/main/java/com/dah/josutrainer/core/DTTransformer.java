package com.dah.josutrainer.core;

public class DTTransformer {
    public static double transformAR(double afterDT) {
        return 0.5 * (afterDT * 3 - 13);
    }

    public static double transformOD(double afterOD) {
        double afterHitWindow = 79.5 - 6 * afterOD;
        double beforeHitWindow = afterHitWindow * 1.5;
        return (79.5 - beforeHitWindow) / 6;
    }
}
