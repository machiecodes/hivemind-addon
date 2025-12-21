package me.machie.collective.modules;

import me.machie.collective.Collective;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class Host extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> port = sgGeneral.add(new IntSetting.Builder()
        .name("port")
        .description("The port to connect to.")
        .defaultValue(6000)
        .range(1, 65535)
        .noSlider()
        .build()
    );

    private HostConnection thread;

    public Host() {
        super(Collective.CATEGORY, "host", "Uses this Meteor instance to manage other instances.");
    }

    @Override
    public void onActivate() {
        Worker worker = Modules.get().get(Worker.class);
        if (worker.isActive()) worker.toggle();

        thread = new HostConnection(port.get());
        if (thread.socket != null) thread.start();
    }

    @Override
    public void onDeactivate() {
        thread.interrupt();
    }
}
