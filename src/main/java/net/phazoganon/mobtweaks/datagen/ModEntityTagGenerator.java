package net.phazoganon.mobtweaks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.phazoganon.mobtweaks.MobTweaks;
import net.phazoganon.mobtweaks.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagGenerator extends EntityTypeTagsProvider {
    public ModEntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MobTweaks.MODID);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Entities.VERY_LOW_AGGRESSION).add(EntityType.SLIME, EntityType.SILVERFISH, EntityType.ENDERMITE, EntityType.HUSK, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.DROWNED, EntityType.ZOMBIFIED_PIGLIN);
        tag(ModTags.Entities.LOW_AGGRESSION).add(EntityType.MAGMA_CUBE, EntityType.SHULKER, EntityType.GHAST, EntityType.BREEZE);
        tag(ModTags.Entities.MEDIUM_AGGRESSION).add(EntityType.SPIDER, EntityType.SKELETON, EntityType.BOGGED, EntityType.STRAY, EntityType.PIGLIN, EntityType.CREEPER, EntityType.GUARDIAN, EntityType.WITCH, EntityType.CAVE_SPIDER);
        tag(ModTags.Entities.HIGH_AGGRESSION).add(EntityType.PILLAGER, EntityType.VEX, EntityType.WITHER_SKELETON, EntityType.ENDERMAN, EntityType.ELDER_GUARDIAN, EntityType.HOGLIN, EntityType.ZOGLIN, EntityType.BLAZE);
        tag(ModTags.Entities.VERY_HIGH_AGGRESSION).add(EntityType.VINDICATOR, EntityType.EVOKER, EntityType.PIGLIN_BRUTE, EntityType.WARDEN, EntityType.WITHER, EntityType.RAVAGER, EntityType.ENDER_DRAGON);
    }
}