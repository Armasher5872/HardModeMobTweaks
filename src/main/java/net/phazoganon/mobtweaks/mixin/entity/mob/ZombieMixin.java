package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
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
    @Inject(method = "killedEntity", at = @At(value = "RETURN"), cancellable = true)
    private void killedEntity(ServerLevel p_219160_, LivingEntity p_219161_, CallbackInfoReturnable<Boolean> cir) {
        boolean flag = super.killedEntity(p_219160_, p_219161_);
        if ((p_219160_.getDifficulty() == Difficulty.EASY || p_219160_.getDifficulty() == Difficulty.NORMAL) && p_219161_ instanceof Villager villager) {
            if (EventHooks.canLivingConvert(p_219161_, EntityType.ZOMBIE_VILLAGER, (timer) -> {
            })) {
                if (p_219160_.getDifficulty() != Difficulty.EASY && this.random.nextBoolean()) {
                    cir.setReturnValue(flag);
                }
                ZombieVillager zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, ConversionParams.single(villager, true, true), (p_370686_) -> {
                    p_370686_.finalizeSpawn(p_219160_, p_219160_.getCurrentDifficultyAt(p_370686_.blockPosition()), EntitySpawnReason.CONVERSION, new Zombie.ZombieGroupData(false, true));
                    p_370686_.setVillagerData(villager.getVillagerData());
                    p_370686_.setGossips(villager.getGossips().store(NbtOps.INSTANCE));
                    p_370686_.setTradeOffers(villager.getOffers().copy());
                    p_370686_.setVillagerXp(villager.getVillagerXp());
                    EventHooks.onLivingConvert(villager, p_370686_);
                    if (!this.isSilent()) {
                        p_219160_.levelEvent(null, 1026, this.blockPosition(), 0);
                    }
                });
                if (zombievillager != null) {
                    flag = false;
                }
            }
        }
        cir.setReturnValue(flag);
    }
}