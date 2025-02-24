package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {
    @Inject(method = "isWearingSafeArmor", at = @At(value = "RETURN"), cancellable = true)
    private static void isWearingSafeArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        Difficulty difficulty = entity.level().getDifficulty();
        int armorCount = 0;
        for (ItemStack itemStack : entity.getArmorAndBodyArmorSlots()) {
            if (itemStack.makesPiglinsNeutral(entity)) {
                armorCount++;
            }
        }
        switch (difficulty) {
            case EASY -> {
                if (armorCount > 0) {
                    cir.setReturnValue(true);
                }
                else {
                    cir.setReturnValue(false);
                }
            }
            case NORMAL -> {
                if (armorCount > 1) {
                    cir.setReturnValue(true);
                }
                else {
                    cir.setReturnValue(false);
                }
            }
            case HARD -> {
                if (armorCount > 3) {
                    cir.setReturnValue(true);
                }
                else {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}