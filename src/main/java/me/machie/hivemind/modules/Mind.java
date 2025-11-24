package me.machie.hivemind.modules;

import me.machie.hivemind.Addon;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.modules.Module;

public class Mind extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> ip = sgGeneral.add(new StringSetting.Builder()
        .name("ip")
        .description("The IP address to connect to.")
        .defaultValue("localhost")
        .build()
    );

    private final Setting<Integer> port = sgGeneral.add(new IntSetting.Builder()
        .name("port")
        .description("The port to connect to.")
        .defaultValue(6000)
        .range(1, 65535)
        .noSlider()
        .build()
    );

    public Mind() {
        super(Addon.CATEGORY, "mind", "Completes tasks for the hive.");
    }
}
