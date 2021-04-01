package kaptainwutax.featureutils.loot.roll;

import kaptainwutax.featureutils.loot.LootContext;

public class BinomialRoll extends LootRoll {

    public final int trials;
    public final float probability;

    public BinomialRoll(int trials, float probability) {
        this.trials = trials;
        this.probability = probability;
    }

    @Override
    public int getCount(LootContext context) {
        int rolls = 0;

        for (int j = 0; j < this.trials; j++) {
            if (context.nextFloat() < this.probability) {
                rolls++;
            }
        }

        return rolls;
    }

}
