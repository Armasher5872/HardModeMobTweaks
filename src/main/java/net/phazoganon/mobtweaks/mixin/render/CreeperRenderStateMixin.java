package net.phazoganon.mobtweaks.mixin.render;

import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.phazoganon.mobtweaks.util.CreeperRendererAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperRenderState.class)
public abstract class CreeperRenderStateMixin implements CreeperRendererAccessor {
    @Unique public int CREEPER_COLOR;
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void init(CallbackInfo ci) {
        this.CREEPER_COLOR = 16777215;
    }
    @Unique
    public int hardModeMobTweaks$getCreeperColor() {
        return CREEPER_COLOR;
    }
    @Unique
    public void hardModeMobTweaks$setCreeperColor(int color) {
        CREEPER_COLOR = color;
    }
}