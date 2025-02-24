package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Spider.class)
public abstract class SpiderMixin extends Monster {
    protected SpiderMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    @Inject(method = "finalizeSpawn", at = @At(value = "HEAD"), cancellable = true)
    private void finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        cir.cancel();
        spawnGroupData = super.finalizeSpawn(level, difficulty, entitySpawnReason, spawnGroupData);
        RandomSource randomsource = level.getRandom();
        int difficultyChance = 0;
        switch (level.getDifficulty()) {
            case NORMAL -> difficultyChance = 2;
            case HARD -> difficultyChance = 6;
        }
        if (randomsource.nextInt(100) <= difficultyChance) {
            Skeleton skeleton = EntityType.SKELETON.create(this.level(), EntitySpawnReason.JOCKEY);
            if (skeleton != null) {
                skeleton.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                skeleton.finalizeSpawn(level, difficulty, entitySpawnReason, null);
                skeleton.startRiding(this);
            }
        }
        if (spawnGroupData == null) {
            spawnGroupData = new Spider.SpiderEffectsGroupData();
            if (level.getDifficulty() == Difficulty.HARD && randomsource.nextFloat() < 0.1F * difficulty.getSpecialMultiplier()) {
                ((Spider.SpiderEffectsGroupData)spawnGroupData).setRandomEffect(randomsource);
            }
        }
        if (spawnGroupData instanceof Spider.SpiderEffectsGroupData spider$spidereffectsgroupdata) {
            Holder<MobEffect> holder = spider$spidereffectsgroupdata.effect;
            if (holder != null) {
                this.addEffect(new MobEffectInstance(holder, -1));
            }
        }
        cir.setReturnValue(spawnGroupData);
    }
}