package net.phazoganon.hardmodemobtweaks.mixin.entity.mob;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
    protected ZombieMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    @Inject(method = "canBreakDoors", at = @At(value = "RETURN"), cancellable = true)
    private void canBreakDoors(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
    @Inject(method = "populateDefaultEquipmentSlots", at = @At(value = "HEAD"))
    private void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_, CallbackInfo ci) {
        float itemChance = 0.0F; //Chance of spawning an item
        boolean randomDiamond = false;
        boolean randomIron = false;
        boolean randomStone = false;
        boolean randomWood = false;
        ItemStack item = new ItemStack(Items.WOODEN_SHOVEL);
        Difficulty difficulty = p_219166_.getDifficulty();
        float randomItem = p_219165_.nextFloat();
        switch (difficulty) {
            case EASY -> itemChance = 0.01F;
            case NORMAL -> itemChance = 0.05F;
            case HARD -> itemChance = 0.15F;
        }
        if (randomItem < itemChance) {
            int randomWeapon = p_219165_.nextInt(10000);
            switch (difficulty) {
                case EASY -> {
                    randomDiamond = randomWeapon < 10;
                    randomIron = randomWeapon >= 10 && randomWeapon < 100;
                    randomStone = randomWeapon >= 100 && randomWeapon < 1000;
                    randomWood = randomWeapon >= 1000 && randomWeapon <= 10000;
                }
                case NORMAL -> {
                    randomDiamond = randomWeapon < 200;
                    randomIron = randomWeapon >= 200 && randomWeapon < 1200;
                    randomStone = randomWeapon >= 1200 && randomWeapon < 3000;
                    randomWood = randomWeapon >= 3000 && randomWeapon <= 10000;
                }
                case HARD -> {
                    randomDiamond = randomWeapon < 500;
                    randomIron = randomWeapon >= 500 && randomWeapon < 3000;
                    randomStone = randomWeapon >= 3000 && randomWeapon < 9000;
                    randomWood = randomWeapon >= 9000 && randomWeapon <= 10000;
                }
            }
            int rand = p_219165_.nextInt(10);
            if (rand < 1) {
                if (randomDiamond) {
                    item = new ItemStack(Items.DIAMOND_AXE);
                }
                if (randomIron) {
                    item = new ItemStack(Items.IRON_AXE);
                }
                if (randomStone) {
                    item = new ItemStack(Items.STONE_AXE);
                }
                if (randomWood) {
                    item = new ItemStack(Items.WOODEN_AXE);
                }
            }
            else if (rand < 4) {
                if (randomDiamond) {
                    item = new ItemStack(Items.DIAMOND_SWORD);
                }
                if (randomIron) {
                    item = new ItemStack(Items.IRON_SWORD);
                }
                if (randomStone) {
                    item = new ItemStack(Items.STONE_SWORD);
                }
                if (randomWood) {
                    item = new ItemStack(Items.WOODEN_SWORD);
                }
            }
            else {
                if (randomDiamond) {
                    item = new ItemStack(Items.DIAMOND_SHOVEL);
                }
                if (randomIron) {
                    item = new ItemStack(Items.IRON_SHOVEL);
                }
                if (randomStone) {
                    item = new ItemStack(Items.STONE_SHOVEL);
                }
            }
            this.setItemSlot(EquipmentSlot.MAINHAND, item);
        }
    }
}