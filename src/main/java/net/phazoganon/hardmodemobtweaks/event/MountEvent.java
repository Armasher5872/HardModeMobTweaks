package net.phazoganon.hardmodemobtweaks.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityMountEvent;
import net.phazoganon.hardmodemobtweaks.HardModeMobTweaks;
import net.phazoganon.hardmodemobtweaks.util.ModGamerules;
import net.phazoganon.hardmodemobtweaks.util.ModTags;

@EventBusSubscriber(modid = HardModeMobTweaks.MODID)
public class MountEvent {
    @SubscribeEvent
    public static void onMount(EntityMountEvent event) {
        if (event.getEntityBeingMounted() instanceof Boat boat) {
            if (event.getEntity() instanceof Monster monster) {
                if (monster.level() instanceof ServerLevel serverLevel) {
                    if (serverLevel.getGameRules().getBoolean(ModGamerules.MOB_VEHICLE_DISMOUNT)) {
                        if (monster.getType().is(ModTags.Entities.VERY_LOW_AGGRESSION)) {
                            if (monster.getHealth() >= 10.0) {
                                event.setCanceled(true);
                            }
                        }
                        if (monster.getType().is(ModTags.Entities.LOW_AGGRESSION)) {
                            if (monster.getHealth() >= 8.0) {
                                event.setCanceled(true);
                            }
                        }
                        if (monster.getType().is(ModTags.Entities.MEDIUM_AGGRESSION)) {
                            if (monster.getHealth() >= 6.0) {
                                event.setCanceled(true);
                            }
                        }
                        if (monster.getType().is(ModTags.Entities.HIGH_AGGRESSION)) {
                            if (monster.getHealth() >= 4.0) {
                                boat.hurt(monster.damageSources().generic(), 6.0F);
                                event.setCanceled(true);
                            }
                        }
                        if (monster.getType().is(ModTags.Entities.VERY_HIGH_AGGRESSION)) {
                            if (monster.getHealth() >= 2.0) {
                                boat.hurt(monster.damageSources().generic(), 6.0F);
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
