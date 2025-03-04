package net.phazoganon.mobtweaks.event;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Bogged;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.phazoganon.mobtweaks.MobTweaks;

@EventBusSubscriber(modid = MobTweaks.MODID)
public class ChangeTargetEvent {
    @SubscribeEvent
    public static void setTarget(LivingChangeTargetEvent event) {
        if (event.getNewAboutToBeSetTarget() != null) {
            LivingEntity entity = event.getEntity();
            LivingEntity target = event.getNewAboutToBeSetTarget();
            Difficulty difficulty = entity.level().getDifficulty();
            boolean target_mobs = (target instanceof Stray || target instanceof Bogged || target instanceof Skeleton || target instanceof Pillager || target instanceof Piglin || target instanceof WitherBoss || target instanceof EnderDragon);
            switch (difficulty) {
                case NORMAL -> {
                    if (entity instanceof Mob mob && target_mobs) {
                        if (Math.random() <= 0.5F) {
                            mob.setLastHurtByMob(event.getOriginalAboutToBeSetTarget());
                            event.setCanceled(true);
                        }
                    }
                }
                case HARD -> {
                    if (entity instanceof Mob mob && target_mobs) {
                        mob.setLastHurtByMob(event.getOriginalAboutToBeSetTarget());
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}