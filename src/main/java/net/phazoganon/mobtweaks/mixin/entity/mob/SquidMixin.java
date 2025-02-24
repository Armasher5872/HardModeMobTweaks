package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AgeableWaterCreature;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Squid.class)
public abstract class SquidMixin extends AgeableWaterCreature {
    protected SquidMixin(EntityType<? extends AgeableWaterCreature> p_364107_, Level p_360956_) {
        super(p_364107_, p_360956_);
    }
    @Inject(method = "registerGoals", at = @At(value = "TAIL"))
    public void registerGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractFish.class, true));
    }
    @Inject(method = "createAttributes", at = @At(value = "RETURN"), cancellable = true)
    private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.setReturnValue(Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.ATTACK_DAMAGE, 6.0D));
    }
}