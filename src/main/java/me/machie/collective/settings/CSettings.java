package me.machie.collective.settings;

import me.machie.collective.systems.collective.CollectiveSystem;
import meteordevelopment.meteorclient.settings.*;

public class CSettings extends Settings {
    private final SettingGroup sgGeneral = getDefaultGroup();

    private final Setting<Mode> mode = sgGeneral.add(new EnumSetting.Builder<Mode>()
        .name("mode")
        .description("")
        .defaultValue(Mode.Manager)
        .build()
    );

    private final Setting<String> ip = sgGeneral.add(new StringSetting.Builder()
        .name("ip")
        .description("The IP address of the host server.")
        .defaultValue("localhost")
        .visible(() -> mode.get() == Mode.Worker)
        .build()
    );

    private final Setting<String> port = sgGeneral.add(new StringSetting.Builder()
        .name("port")
        .description("The port used for connections.")
        .defaultValue("6000")
        .build()
    );

    public enum Mode {
        Manager,
        Worker
    }

    public static CSettings get() {
        return CollectiveSystem.get().cSettings;
    }
}
