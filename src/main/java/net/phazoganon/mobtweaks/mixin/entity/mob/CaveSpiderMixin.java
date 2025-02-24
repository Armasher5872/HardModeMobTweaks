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
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CaveSpider.class)
public abstract class CaveSpiderMixin extends Spider {
    public CaveSpiderMixin(EntityType<? extends Spider> p_33786_, Level p_33787_) {
        super(p_33786_, p_33787_);
    }
    @Inject(method = "finalizeSpawn", at = @At(value = "RETURN"), cancellable = true)
    private void finalizeSpawn(ServerLevelAccessor p_32259_, DifficultyInstance p_32260_, EntitySpawnReason p_363852_, SpawnGroupData p_32262_, CallbackInfoReturnable<SpawnGroupData> cir) {
        SpawnGroupData groupData = super.finalizeSpawn(p_32259_, p_32260_, p_363852_, p_32262_);
        RandomSource randomSource = p_32259_.getRandom();
        if (groupData == null) {
            groupData = new Spider.SpiderEffectsGroupData();
            if (p_32259_.getDifficulty() == Difficulty.HARD && randomSource.nextFloat() < 0.1F*p_32260_.getSpecialMultiplier()) {
                ((Spider.SpiderEffectsGroupData) groupData).setRandomEffect(randomSource);
            }
        }
        if (groupData instanceof Spider.SpiderEffectsGroupData spider$spidereffectsgroupdata) {
            Holder<MobEffect> holder = spider$spidereffectsgroupdata.effect;
            if (holder != null) {
                this.addEffect(new MobEffectInstance(holder, -1));
            }
        }
        cir.setReturnValue(groupData);
    }
}