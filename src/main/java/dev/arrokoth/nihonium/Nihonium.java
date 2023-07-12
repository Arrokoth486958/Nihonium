package dev.arrokoth.nihonium;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Nihonium.MOD_ID, value = Dist.CLIENT)
@Mod(Nihonium.MOD_ID)
public class Nihonium {

    public static final String MOD_ID = "nihonium";

    public Nihonium() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void renderEvent(TickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (!Objects.isNull(mc.level)) {
            int x = mc.level.getRandom().nextInt(48) + 16;
            try {
                Field fieldFps = mc.getClass().getDeclaredField("fps");
                fieldFps.setAccessible(true);
                fieldFps.set(mc, Math.max((Integer) fieldFps.get(mc), 512) * x);

                Field fieldFrames = mc.getClass().getDeclaredField("frames");
                fieldFrames.setAccessible(true);
                fieldFrames.set(mc, Math.max((Integer) fieldFrames.get(mc), 512) * x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
