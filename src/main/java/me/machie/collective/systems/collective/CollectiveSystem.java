package me.machie.collective.systems.collective;

import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.System;
import meteordevelopment.meteorclient.systems.Systems;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import net.minecraft.nbt.NbtCompound;

public class CollectiveSystem extends System<CollectiveSystem> {
    public final Settings settings = new Settings();
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final SettingGroup sgMenu = settings.createGroup("Menu");

    // General



    // Menu

    private final KeybindSetting menuBind = sgMenu.add(new KeybindSetting.Builder()
        .name("key")
        .description("Hold this key to open the task assignment menu.")
        .build()
    );

    private final Setting<SettingColor> menuBgColor = sgMenu.add(new ColorSetting.Builder()
        .name("background-color")
        .description("")
        .defaultValue(new SettingColor(227, 196, 245, 10))
        .build()
    );

    private final Setting<SettingColor> menuLineColor = sgMenu.add(new ColorSetting.Builder()
        .name("line-color")
        .description("")
        .defaultValue(new SettingColor(5, 139, 221))
        .build()
    );

    private final Setting<SettingColor> menuTextColor = sgMenu.add(new ColorSetting.Builder()
        .name("text-color")
        .description("")
        .defaultValue(new SettingColor(255, 255, 255))
        .build()
    );



    public ManagerConnection managerConnection;
    public WorkerConnection workerConnection;

    public CollectiveSystem() {
        super("collective");
    }

    public static CollectiveSystem get() {
        return Systems.get(CollectiveSystem.class);
    }

    @Override
    public NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();

        tag.put("settings", settings.toTag());

        return tag;
    }

    @Override
    public CollectiveSystem fromTag(NbtCompound tag) {
        if (tag.contains("settings")) settings.fromTag(tag.getCompoundOrEmpty("settings"));

        return this;
    }
}
