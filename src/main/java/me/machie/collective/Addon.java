package me.machie.collective;

import com.mojang.logging.LogUtils;
import me.machie.collective.modules.Client;
import me.machie.collective.modules.Server;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Hivemind");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Hivemind");

        // Modules
        Modules.get().add(new Client());
        Modules.get().add(new Server());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "me.machie.hivemind";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("machiecodes", "hivemind-addon");
    }
}
