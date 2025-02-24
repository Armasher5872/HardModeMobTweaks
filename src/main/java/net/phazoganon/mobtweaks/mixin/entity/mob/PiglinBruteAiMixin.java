package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinBruteAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PiglinBruteAi.class)
public abstract class PiglinBruteAiMixin {
    @Inject(method = "findNearestValidAttackTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/Brain;getMemory(Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;)Ljava/util/Optional;", ordinal = 0), cancellable = true)
    private static void findNearestValidAttackTarget(ServerLevel level, AbstractPiglin piglin, CallbackInfoReturnable<Optional<? extends LivingEntity>> cir) {
        cir.setReturnValue(piglin.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER).filter((livingEntity) -> {
            if (livingEntity instanceof Player player) {
                Difficulty difficulty = player.level().getDifficulty();
                int armorCount = 0;
                if (difficulty == Difficulty.EASY) {
                    for (ItemStack itemStack : player.getArmorAndBodyArmorSlots()) {
                        if (itemStack.makesPiglinsNeutral(player)) {
                            armorCount++;
                        }
                    }
                    if (armorCount > 3) {
                        return false;
                    }
                }
            }
            return livingEntity.closerThan(piglin, 12.0);
        }));
    }
}