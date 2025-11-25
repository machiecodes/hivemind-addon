package me.machie.hivemind.modules;

import me.machie.hivemind.Addon;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class Server extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Integer> port = sgGeneral.add(new IntSetting.Builder()
        .name("port")
        .description("The port to connect to.")
        .defaultValue(6000)
        .range(1, 65535)
        .noSlider()
        .build()
    );

    private ServerThread thread;

    public Server() {
        super(Addon.CATEGORY, "server", "Uses this Meteor instance to manage other instances.");
    }

    @Override
    public void onActivate() {
        Client client = Modules.get().get(Client.class);
        if (client.isActive()) client.toggle();

        thread = new ServerThread(port.get());
        if (thread.socket != null) thread.start();
    }

    @Override
    public void onDeactivate() {
        thread.interrupt();
    }
}
