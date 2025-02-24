package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.UUID;

@Mixin(ZombieVillager.class)
public abstract class ZombieVillagerMixin extends Zombie implements VillagerDataHolder {
    @Shadow private Tag gossips;
    @Shadow private MerchantOffers tradeOffers;
    @Shadow private int villagerXp;
    @Shadow private UUID conversionStarter;
    @Unique private boolean wasInfected;
    public ZombieVillagerMixin(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
    }
    @ModifyConstant(method = "mobInteract", constant = @Constant(intValue = 2401))
    private int mobInteract2401(int constant) {
        return 1;
    }
    @ModifyConstant(method = "mobInteract", constant = @Constant(intValue = 3600))
    private int mobInteract3600(int constant) {
        return 100;
    }
    @Inject(method = "finishConversion", at = @At(value = "HEAD"), cancellable = true)
    private void finishConversion(ServerLevel serverLevel, CallbackInfo ci) {
        ci.cancel();
        this.convertTo(EntityType.VILLAGER, ConversionParams.single(this, false, false), (p_375894_) -> {
            Iterator var3 = this.dropPreservedEquipment(serverLevel, (p_351901_) -> {
                return !EnchantmentHelper.has(p_351901_, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE);
            }).iterator();
            while (var3.hasNext()) {
                EquipmentSlot equipmentslot = (EquipmentSlot)var3.next();
                SlotAccess slotaccess = p_375894_.getSlot(equipmentslot.getIndex() + 300);
                slotaccess.set(this.getItemBySlot(equipmentslot));
            }
            Mob mob = (Mob) this.self();
            if (mob.getSpawnType() == EntitySpawnReason.CONVERSION) {
                wasInfected = true;
            }
            p_375894_.setVillagerData(this.getVillagerData());
            if (this.gossips != null) {
                p_375894_.setGossips(this.gossips);
            }
            if (this.tradeOffers != null) {
                p_375894_.setOffers(this.tradeOffers.copy());
            }
            p_375894_.setVillagerXp(this.villagerXp);
            p_375894_.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(p_375894_.blockPosition()), EntitySpawnReason.CONVERSION, null);
            p_375894_.refreshBrain(serverLevel);
            if (this.conversionStarter != null) {
                Player player = serverLevel.getPlayerByUUID(this.conversionStarter);
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.CURED_ZOMBIE_VILLAGER.trigger((ServerPlayer)player, this, p_375894_);
                    if (!wasInfected) {
                        serverLevel.onReputationEvent(ReputationEventType.ZOMBIE_VILLAGER_CURED, player, p_375894_);
                    }
                }
            }
            p_375894_.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
            if (!this.isSilent()) {
                serverLevel.levelEvent(null, 1027, this.blockPosition(), 0);
            }
            EventHooks.onLivingConvert(this, p_375894_);
        });
    }
}