package net.phazoganon.mobtweaks.mixin.render;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChickenModel.class)
public class ChickenModelMixin extends EntityModel<ChickenRenderState> {
    @Shadow @Final private ModelPart head;
    @Shadow @Final private ModelPart rightLeg;
    @Shadow @Final private ModelPart leftLeg;
    @Shadow @Final private ModelPart rightWing;
    @Shadow @Final private ModelPart leftWing;
    protected ChickenModelMixin(ModelPart root) {
        super(root);
    }
    /**
     * @author PhazoGanon
     * @reason Attempts to make the wing movement on baby chickens less wonky
     */
    @Overwrite
    public void setupAnim(ChickenRenderState p_364616_) {
        super.setupAnim(p_364616_);
        float f;
        boolean baby = p_364616_.isBaby;
        if (baby) {
            f = ((Mth.sin(p_364616_.flap)+1F)*(p_364616_.flapSpeed))/3;
        }
        else {
            f = (Mth.sin(p_364616_.flap)+1F)*p_364616_.flapSpeed;
        }
        this.head.xRot = (float) Math.toRadians(p_364616_.xRot);
        this.head.yRot = (float) Math.toRadians(p_364616_.yRot);
        float f1 = p_364616_.walkAnimationSpeed;
        float f2 = p_364616_.walkAnimationPos;
        this.rightLeg.xRot = Mth.cos(f2*0.6662F)*1.4F*f1;
        this.leftLeg.xRot = Mth.cos(f2*0.6662F+(float)Math.PI)*1.4F*f1;
        if (baby) {
            this.rightWing.zRot = f;
            this.rightWing.x = -f/2;
            this.leftWing.zRot = -f;
            this.leftWing.x = f/2;
        }
        else {
            this.rightWing.zRot = f;
            this.leftWing.zRot = -f;
        }
    }
}
