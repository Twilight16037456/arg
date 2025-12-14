package yourpackage;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class ArgRenderMod implements ClientModInitializer {

    private KeyBinding activateKey;
    private boolean activated = false;
    private long lastUpdate = 0;

    @Override
    public void onInitializeClient() {

        activateKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.argmod.activate",
                GLFW.GLFW_KEY_F9,
                "key.categories.misc"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (activateKey.wasPressed()) {
                activated = true;
                ArgState.ACTIVE = true;
                System.out.println("[ARG] Activated");
            }

            if (!activated) return;

            long now = System.currentTimeMillis();
            if (now - lastUpdate < 300) return;
            lastUpdate = now;

            GameOptions opts = MinecraftClient.getInstance().options;
            int current = opts.getViewDistance().getValue();
            int target = 3;

            if (current > target) {
                opts.getViewDistance().setValue(current - 1);
            }
        });
    }
}
