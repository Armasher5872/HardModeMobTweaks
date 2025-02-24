package net.phazoganon.mobtweaks.util;

import net.minecraft.world.level.GameRules;

public class ModGamerules {
    public static final GameRules.Key<GameRules.BooleanValue> MOB_VEHICLE_DISMOUNT = GameRules
            .register("doMobVehicleDismount", GameRules.Category.MOBS, GameRules.BooleanValue.create(false));
}