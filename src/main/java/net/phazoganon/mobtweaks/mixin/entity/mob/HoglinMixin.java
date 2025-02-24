package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Hoglin.class)
public abstract class HoglinMixin {
    @Shadow protected abstract void finishConversion();
    @Inject(method = "finalizeSpawn", at = @At(value = "RETURN"))
    private void finalizeSpawn(ServerLevelAccessor p_34508_, DifficultyInstance p_34509_, EntitySpawnReason p_364113_, SpawnGroupData p_34511_, CallbackInfoReturnable<SpawnGroupData> cir) {
        Difficulty difficulty = p_34509_.getDifficulty();
        if (difficulty.equals(Difficulty.HARD)) {
            if (p_34508_.getRandom().nextFloat() < 0.05F) {
                this.finishConversion();
            }
        }
    }
}