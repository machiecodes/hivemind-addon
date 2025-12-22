package me.machie.collective.systems.collective;

import me.machie.collective.settings.CSettings;
import me.machie.collective.settings.TSettings;
import meteordevelopment.meteorclient.systems.System;
import meteordevelopment.meteorclient.systems.Systems;
import net.minecraft.nbt.NbtCompound;

public class CollectiveSystem extends System<CollectiveSystem> {
    public final TSettings tSettings = new TSettings();
    public final CSettings cSettings = new CSettings();

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

        tag.put("tSettings", tSettings.toTag());
        tag.put("cSettings", cSettings.toTag());

        return tag;
    }

    @Override
    public CollectiveSystem fromTag(NbtCompound tag) {
        if (tag.contains("tSettings")) tSettings.fromTag(tag.getCompoundOrEmpty("tSettings"));
        if (tag.contains("cSettings")) tSettings.fromTag(tag.getCompoundOrEmpty("cSettings"));

        return this;
    }
}
