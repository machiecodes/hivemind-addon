package me.machie.collective.settings;

import me.machie.collective.systems.collective.CollectiveSystem;
import meteordevelopment.meteorclient.settings.*;

public class TSettings extends Settings {
    private final SettingGroup sgGeneral = getDefaultGroup();

    public static TSettings get() {
        return CollectiveSystem.get().tSettings;
    }
}
