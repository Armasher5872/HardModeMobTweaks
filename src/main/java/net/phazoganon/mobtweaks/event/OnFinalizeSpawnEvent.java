package net.phazoganon.mobtweaks.event;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.phazoganon.mobtweaks.MobTweaks;

import java.util.Locale;

@EventBusSubscriber(modid = MobTweaks.MODID)
public abstract class OnFinalizeSpawnEvent {
    @SubscribeEvent
    public static void onFinalizeSpawn(FinalizeSpawnEvent finalizeSpawn) {
        Mob mob = finalizeSpawn.getEntity();
        Difficulty difficulty = mob.level().getDifficulty();
        //Drowned, Husk, Zombie
        if (mob instanceof Zombie) {
            ((Zombie) mob).setCanBreakDoors(false);
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier(prefix("easy_knockback_resistance"), (Math.random()*0.05000000074505806), AttributeModifier.Operation.ADD_VALUE));
                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, -1, 0, false, false, false));
            }
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier(prefix("normal_knockback_resistance"), (Math.random()*0.12000000074505806), AttributeModifier.Operation.ADD_VALUE));
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier(prefix("hard_knockback_resistance"), (Math.random()*0.30000000074505806), AttributeModifier.Operation.ADD_VALUE));
                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0, false, false, false));
            }
        }
        //Elder Guardian
        if (mob instanceof ElderGuardian) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 40.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+40.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 80.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+80.0F);
            }
        }
        //Ender Dragon
        if (mob instanceof EnderDragon) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 200.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+200.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 400.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+400.0F);
            }
        }
        //Goat
        if (mob instanceof Goat) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.ATTACK_KNOCKBACK).addPermanentModifier(new AttributeModifier(prefix("normal_attack_knockback"), 2.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.ATTACK_KNOCKBACK).addPermanentModifier(new AttributeModifier(prefix("hard_attack_knockback"), 4.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
        }
        //Mooshroom
        if (mob instanceof MushroomCow mushroomCow) {
            if (Math.random() <= 0.1F) {
                mushroomCow.setVariant(MushroomCow.Variant.BROWN);
            }
        }
        //Wither
        if (mob instanceof WitherBoss) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 150.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+150.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  300.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+300.0F);
            }
        }
    }
    private static ResourceLocation prefix(String string) {
        return ResourceLocation.fromNamespaceAndPath(MobTweaks.MODID, string.toLowerCase(Locale.ROOT));
    }
}