package net.phazoganon.mobtweaks.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.phazoganon.mobtweaks.util.CreeperRendererAccessor;

@OnlyIn(Dist.CLIENT)
public class CreeperBlockLayer extends RenderLayer<CreeperRenderState, CreeperModel> {
    private final ResourceLocation resourceLocation;
    public CreeperBlockLayer(RenderLayerParent<CreeperRenderState, CreeperModel> renderer, ResourceLocation resourceLocation) {
        super(renderer);
        this.resourceLocation = resourceLocation;
    }
    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CreeperRenderState creeperRenderState, float v, float v1) {
        boolean flag = creeperRenderState.appearsGlowing && creeperRenderState.isInvisible;
        int color = 0;
        if (!creeperRenderState.isInvisible || flag) {
            EntityModel<CreeperRenderState> entityModel = this.getParentModel();
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(this.resourceLocation));
            if (creeperRenderState instanceof CreeperRendererAccessor rendererAccessor) {
                color = rendererAccessor.hardModeMobTweaks$getCreeperColor();
            }
            entityModel.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, color);
            System.out.println("Color: " + color);
        }
    }
}