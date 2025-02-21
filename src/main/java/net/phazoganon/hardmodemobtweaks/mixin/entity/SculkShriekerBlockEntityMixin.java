package net.phazoganon.hardmodemobtweaks.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SculkShriekerBlockEntity.class)
public abstract class SculkShriekerBlockEntityMixin extends BlockEntity {
    @Shadow
    private int warningLevel;
    public SculkShriekerBlockEntityMixin(BlockPos pos, BlockState blockState) {
        super(BlockEntityType.SCULK_SHRIEKER, pos, blockState);
    }
    @Inject(method = "trySummonWarden", at = @At(value = "RETURN"), cancellable = true)
    private void trySummonWarden(ServerLevel level, CallbackInfoReturnable<Boolean> cir) {
        int warning = 5;
        Difficulty difficulty = level.getDifficulty();
        switch (difficulty) {
            case NORMAL -> warning = 4;
            case HARD -> warning = 3;
        }
        cir.setReturnValue(this.warningLevel >= warning && SpawnUtil.trySpawnMob(EntityType.WARDEN, EntitySpawnReason.TRIGGERED, level, this.getBlockPos(), 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER, false).isPresent());
    }
}