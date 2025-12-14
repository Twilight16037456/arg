package yourpackage;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogRenderer;
import net.minecraft.client.render.FogShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogMixin {

    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void applyFog(Camera camera, FogRenderer.FogType type, float viewDistance,
                                 boolean thickFog, float tickDelta, CallbackInfo ci) {

        RenderSystem.setShaderFogStart(1.0f);
        RenderSystem.setShaderFogEnd(10.0f);
        RenderSystem.setShaderFogShape(FogShape.SPHERE);
        ci.cancel();
    }
}
