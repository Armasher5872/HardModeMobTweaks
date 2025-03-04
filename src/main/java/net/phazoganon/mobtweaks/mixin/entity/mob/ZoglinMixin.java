package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zoglin.class)
public abstract class ZoglinMixin {
    @Inject(method = "isTargetable", at = @At(value = "RETURN"), cancellable = true)
    private void isTargetable(ServerLevel level, LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityType<?> entityType = entity.getType();
        cir.setReturnValue(cir.getReturnValue() && entityType != EntityType.HOGLIN);
    }
}