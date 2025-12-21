package me.machie.collective.modules;

import me.machie.collective.Addon;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class Client extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> ip = sgGeneral.add(new StringSetting.Builder()
        .name("ip")
        .description("The IP address to connect to.")
        .defaultValue("localhost")
        .build()
    );

    private final Setting<Integer> port = sgGeneral.add(new IntSetting.Builder()
        .name("port")
        .description("The port used for connections.")
        .defaultValue(6000)
        .range(1,65535)
        .noSlider()
        .build()
    );

    private Connection connection;

    public Client() {
        super(Addon.CATEGORY, "client", "Uses this Meteor instance to complete tasks.");
    }

    @Override
    public void onActivate() {
        Server server = Modules.get().get(Server.class);
        if (server.isActive()) server.toggle();

        connection = new Connection(ip.get(), port.get());
        if (connection.socket != null) connection.start();
    }

    @Override
    public void onDeactivate() {
        connection.interrupt();
    }
}
