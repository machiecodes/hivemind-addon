package me.machie.hivemind.modules;

import me.machie.hivemind.Addon;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;

public class Hive extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> port = sgGeneral.add(new IntSetting.Builder()
        .name("port")
        .description("The port used for connections.")
        .defaultValue(6000)
        .range(1, 65535)
        .noSlider()
        .build()
    );

    public Hive() {
        super(Addon.CATEGORY, "hive", "Controls connected minds.");
    }
}
