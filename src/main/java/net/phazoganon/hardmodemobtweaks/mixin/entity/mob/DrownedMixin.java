package net.phazoganon.hardmodemobtweaks.mixin.entity.mob;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Drowned.class)
public abstract class DrownedMixin extends Zombie {
    public DrownedMixin(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
    }
    @Inject(method = "populateDefaultEquipmentSlots", at = @At(value = "HEAD"))
    private void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance, CallbackInfo ci) {
        float randomValue = 0.0F;
        switch (difficultyInstance.getDifficulty()) {
            case EASY -> randomValue = 0.9F;
            case NORMAL -> randomValue = 0.75F;
            case HARD -> randomValue = 0.5F;
        }
        if (randomSource.nextFloat() > randomValue) {
            int randomWeapon = randomSource.nextInt(16);
            if (randomWeapon < 10) {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));
            }
            else {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
            }
        }
    }
}