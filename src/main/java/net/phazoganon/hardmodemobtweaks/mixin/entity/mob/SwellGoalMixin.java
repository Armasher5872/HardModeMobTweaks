package net.phazoganon.hardmodemobtweaks.mixin.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.pathfinder.Path;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwellGoal.class)
interface SwellGoalAccessor {
    @Accessor("target")
    LivingEntity getTarget();
    @Accessor("creeper")
    Creeper getCreeper();
}

@Mixin(SwellGoal.class)
public abstract class SwellGoalMixin {
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Creeper;setSwellDir(I)V", ordinal = 3))
    public void tick(CallbackInfo ci) {
        SwellGoal goal = (SwellGoal) (Object) this;
        Creeper creeper = ((SwellGoalAccessor) goal).getCreeper();
        LivingEntity target = ((SwellGoalAccessor) goal).getTarget();
        if (target == null) return;
        if (target.level().getDifficulty() == Difficulty.HARD) {
            PathNavigation navigation = creeper.getNavigation();
            double target_distance = creeper.distanceTo(target);
            if (!navigation.isInProgress() || navigation.isDone()) {
                double target_rad = Math.atan2(target.getZ()-creeper.getZ(), target.getX()-creeper.getX());
                double distance_from_target = Mth.clamp(1, target_distance, 2.5);
                double new_rad = target_rad+Math.PI/4;
                Path path = navigation.createPath(new BlockPos(
                        (int) (creeper.getX() + Math.cos(new_rad) * distance_from_target),
                        (int) (creeper.getY()),
                        (int) (creeper.getZ() + Math.sin(new_rad) * distance_from_target)
                ), 0);
                navigation.moveTo(path, 2.4D);
            }
        }
    }
}