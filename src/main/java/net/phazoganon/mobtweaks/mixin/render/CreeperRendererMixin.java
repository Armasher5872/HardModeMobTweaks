package net.phazoganon.mobtweaks.mixin.render;

import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import net.phazoganon.mobtweaks.client.layer.CreeperBlockLayer;
import net.phazoganon.mobtweaks.util.CreeperAccessor;
import net.phazoganon.mobtweaks.util.CreeperRendererAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperRenderer.class)
public abstract class CreeperRendererMixin extends MobRenderer<Creeper, CreeperRenderState, CreeperModel> {
    @Shadow @Final private static ResourceLocation CREEPER_LOCATION;
    public CreeperRendererMixin(EntityRendererProvider.Context p_174304_, CreeperModel p_174305_, float p_174306_) {
        super(p_174304_, p_174305_, p_174306_);
    }
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void init(EntityRendererProvider.Context p_173958_, CallbackInfo ci) {
        this.addLayer(new CreeperBlockLayer(this, CREEPER_LOCATION));
    }
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/monster/Creeper;Lnet/minecraft/client/renderer/entity/state/CreeperRenderState;F)V", at = @At(value = "TAIL"))
    private void creeperRenderState(Creeper p_364394_, CreeperRenderState p_361451_, float p_364659_, CallbackInfo ci) {
        if (p_361451_ instanceof CreeperRendererAccessor creeperRendererAccessor && (p_364394_ instanceof CreeperAccessor creeperAccessor)) {
            creeperRendererAccessor.hardModeMobTweaks$setCreeperColor(creeperAccessor.hardModeMobTweaks$getCreeperBlockColor());
        }
    }
}