package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherSkeleton.class)
public abstract class WitherSkeletonMixin extends AbstractSkeleton {
    protected WitherSkeletonMixin(EntityType<? extends AbstractSkeleton> p_32133_, Level p_32134_) {
        super(p_32133_, p_32134_);
    }
    @Inject(method = "populateDefaultEquipmentSlots", at = @At(value = "RETURN"))
    private void populateDefaultEquipmentSlots(RandomSource p_219154_, DifficultyInstance p_219155_, CallbackInfo ci) {
        Difficulty difficulty = p_219155_.getDifficulty();
        ItemStack itemStack = new ItemStack(Items.STONE_SWORD);
        int difficultyChance = 0;
        switch (difficulty) {
            case EASY -> difficultyChance = 1;
            case NORMAL -> difficultyChance = 2;
            case HARD -> difficultyChance = 4;
        }
        if (p_219154_.nextInt(100) <= difficultyChance) {
            itemStack = new ItemStack(Items.BOW);
        }
        this.setItemSlot(EquipmentSlot.MAINHAND, itemStack);
    }
}