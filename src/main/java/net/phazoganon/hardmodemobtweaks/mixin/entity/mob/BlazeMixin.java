package net.phazoganon.hardmodemobtweaks.mixin.entity.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Blaze.class)
public abstract class BlazeMixin extends Monster {
    protected BlazeMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    public boolean doHurtTarget(ServerLevel serverLevel, Entity entity) {
        if (super.doHurtTarget(serverLevel, entity)) {
            if (entity instanceof LivingEntity) {
                int i = 3;
                switch (this.level().getDifficulty()) {
                    case NORMAL -> i = 6;
                    case HARD -> i = 12;
                }
                entity.setRemainingFireTicks(i*20);
                return true;
            }
        }
        return false;
    }
}
