package me.machie.collective.util;

import meteordevelopment.meteorclient.utils.player.ChatUtils;
import net.minecraft.client.MinecraftClient;

public class SafeLoggingThread extends Thread {
    MinecraftClient client =  MinecraftClient.getInstance();

    protected void info(String message, Object... args) {
        client.execute(() -> ChatUtils.infoPrefix("Hivemind", message, args));
    }

    protected void warning(String message, Object... args) {
        client.execute(() -> ChatUtils.warningPrefix("Hivemind", message, args));
    }

    protected void error(String message, Object... args) {
        client.execute(() -> ChatUtils.errorPrefix("Hivemind", message, args));
    }
}
