package yourpackage;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(GameRenderer.class)
public class GlitchMixin {

    private static final Random RANDOM = new Random();

    @Inject(method = "render", at = @At("HEAD"))
    private void glitch(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {

        if (!ArgState.ACTIVE) return;

        float shakeX = (RANDOM.nextFloat() - 0.5f) * 0.08f;
        float shakeY = (RANDOM.nextFloat() - 0.5f) * 0.08f;

        MinecraftClient.getInstance().gameRenderer.getCamera()
                .setRotation(
                        MinecraftClient.getInstance().gameRenderer.getCamera().getYaw() + shakeX * 10,
                        MinecraftClient.getInstance().gameRenderer.getCamera().getPitch() + shakeY * 10
                );
    }
}
