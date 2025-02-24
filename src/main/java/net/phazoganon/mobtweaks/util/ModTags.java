package net.phazoganon.mobtweaks.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.phazoganon.mobtweaks.MobTweaks;

public class ModTags {
    public static class Entities {
        public static final TagKey<EntityType<?>> VERY_LOW_AGGRESSION = tag("very_low_aggression");
        public static final TagKey<EntityType<?>> LOW_AGGRESSION = tag("low_aggression");
        public static final TagKey<EntityType<?>> MEDIUM_AGGRESSION = tag("medium_aggression");
        public static final TagKey<EntityType<?>> HIGH_AGGRESSION = tag("high_aggression");
        public static final TagKey<EntityType<?>> VERY_HIGH_AGGRESSION = tag("very_high_aggression");
        private static TagKey<EntityType<?>> tag(String name) {
            return createEntity(ResourceLocation.fromNamespaceAndPath(MobTweaks.MODID, name));
        }
        private static TagKey<EntityType<?>> createEntity(ResourceLocation name) {
            return TagKey.create(Registries.ENTITY_TYPE, name);
        }
    }
}