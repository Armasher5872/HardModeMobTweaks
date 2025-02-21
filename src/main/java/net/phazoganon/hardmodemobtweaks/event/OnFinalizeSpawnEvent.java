package net.phazoganon.hardmodemobtweaks.event;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.phazoganon.hardmodemobtweaks.HardModeMobTweaks;

import java.util.Locale;

@EventBusSubscriber(modid = HardModeMobTweaks.MODID)
public abstract class OnFinalizeSpawnEvent {
    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent finalizeSpawn) {
        Mob mob = finalizeSpawn.getEntity();
        Difficulty difficulty = mob.level().getDifficulty();
        //Blaze
        if (mob instanceof Blaze) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 5.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+5.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 20.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+20.0F);
            }
        }
        //Cave Spider
        if (mob instanceof CaveSpider) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 4.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+4.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 10.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+10.0F);
            }
        }
        //Creeper
        if (mob instanceof Creeper) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -8.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-8.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 8.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+8.0F);
            }
        }
        //Drowned, Husk, Zombie
        if (mob instanceof Zombie) {
            ((Zombie) mob).setCanBreakDoors(false);
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -5.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier(prefix("easy_knockback_resistance"), (Math.random()*0.05000000074505806), AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-5.0F);
                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, -1, 0, false, false, false));
            }
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier(prefix("normal_knockback_resistance"), (Math.random()*0.12000000074505806), AttributeModifier.Operation.ADD_VALUE));
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 10.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier(prefix("hard_knockback_resistance"), (Math.random()*0.30000000074505806), AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+10.0F);
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
        //Enderman
        if (mob instanceof EnderMan) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -10.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-10.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 10.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+10.0F);
            }
        }
        //Evoker
        if (mob instanceof Evoker) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -6.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-6.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 11.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+11.0F);
            }
        }
        //Ghast
        if (mob instanceof Ghast) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -2.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-2.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 6.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+6.0F);
            }
        }
        //Guardian
        if (mob instanceof Guardian) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -10.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-10.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"), 10.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+10.0F);
            }
        }
        //Piglin
        if (mob instanceof Piglin) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 8.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+8.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  16.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+16.0F);
            }
        }
        //Pillager
        if (mob instanceof Pillager) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 8.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+8.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  12.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+12.0F);
            }
        }
        //Ravager
        if (mob instanceof Ravager) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -25.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-25.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  50.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+50.0F);
            }
        }
        //Skeleton
        if (mob instanceof Skeleton) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -4.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-4.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  8.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+8.0F);
            }
        }
        //Spider
        if (mob instanceof Spider) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -4.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-4.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  8.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+8.0F);
            }
        }
        //Witch
        if (mob instanceof Witch) {
            if (difficulty == Difficulty.EASY) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("easy_hp"), -8.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()-8.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  12.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+12.0F);
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
        //Wither Skeleton
        if (mob instanceof WitherSkeleton) {
            if (difficulty == Difficulty.NORMAL) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("normal_hp"), 6.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+6.0F);
            }
            if (difficulty == Difficulty.HARD) {
                mob.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier(prefix("hard_hp"),  16.0F, AttributeModifier.Operation.ADD_VALUE));
                mob.setHealth(mob.getHealth()+16.0F);
            }
        }
    }
    private static ResourceLocation prefix(String string) {
        return ResourceLocation.fromNamespaceAndPath(HardModeMobTweaks.MODID, string.toLowerCase(Locale.ROOT));
    }
}