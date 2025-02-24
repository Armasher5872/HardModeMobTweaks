package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.monster.Blaze;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.world.entity.monster.Blaze$BlazeAttackGoal")
public abstract class BlazeGoalMixin {
    @Shadow
    @Final
    private Blaze blaze;
    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 2.297))
    private double randomize(double constant) {
        double random = 3.0;
        Difficulty difficulty = this.blaze.level().getDifficulty();
        switch (difficulty) {
            case NORMAL -> random = 2.0;
            case HARD -> random = 1.0;
        }
        return random;
    }
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 60))
    private int windup(int constant) {
        int startup = 60;
        Difficulty difficulty = this.blaze.level().getDifficulty();
        switch (difficulty) {
            case NORMAL -> startup = 40;
            case HARD -> startup = 10;
        }
        return startup;
    }
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 100))
    private int cooldown(int constant) {
        int timeout = 100;
        Difficulty difficulty = this.blaze.level().getDifficulty();
        switch (difficulty) {
            case NORMAL -> timeout = 75;
            case HARD -> timeout = 40;
        }
        return timeout;
    }
}