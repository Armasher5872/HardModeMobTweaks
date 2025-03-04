package net.phazoganon.mobtweaks.mixin.entity.mob;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.phazoganon.mobtweaks.util.CreeperAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public class CreeperMixin extends Monster implements CreeperAccessor {
    @Unique
    public int CREEPER_BLOCK_COLOR;
    public CreeperMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void tickPos(CallbackInfo ci) {
        hardModeMobTweaks$createCreeperColor(this.level(), this.blockPosition());
    }
    @Unique
    private void hardModeMobTweaks$createCreeperColor(Level level, BlockPos blockPos) {
        BlockState blockState = level.getBlockState(blockPos.below());
        CREEPER_BLOCK_COLOR =
        blockState.is(Blocks.AMETHYST_BLOCK) ? 8807103 : blockState.is(Blocks.ANDESITE) ? 8947849 : blockState.is(Blocks.BLUE_ICE) ? 7645437 : blockState.is(Blocks.DEEPSLATE) ? 5263443 : blockState.is(Blocks.DIORITE) ? 12434621 :
        blockState.is(Blocks.DIRT) ? 8806467 : blockState.is(Blocks.DRIPSTONE_BLOCK) ? 8809565 : blockState.is(Blocks.GRANITE) ? 9791318 : blockState.is(Blocks.GRAVEL) ? 8683391 : blockState.is(Blocks.ICE) ? 9221629 : blockState.is(Blocks.MOSS_BLOCK)
        ? 5860909 : blockState.is(Blocks.MUD) ? 3946813 : blockState.is(Blocks.PODZOL) ? 6045464 : blockState.is(Blocks.RED_SAND) || blockState.is(Blocks.RED_SANDSTONE) ? 12543777 : blockState.is(Blocks.SAND) || blockState.is(Blocks.SANDSTONE)
        ? 14405539 : blockState.is(Blocks.SNOW_BLOCK) || blockState.is(Blocks.SNOW) ? 16383742 : blockState.is(Blocks.STONE) ? 8289918 : BiomeColors.getAverageGrassColor(level, blockPos);
    }
    @Unique
    public int hardModeMobTweaks$getCreeperBlockColor() {
        return CREEPER_BLOCK_COLOR;
    }
}