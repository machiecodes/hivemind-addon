package me.machie.collective;

import com.mojang.logging.LogUtils;
import me.machie.collective.modules.Worker;
import me.machie.collective.modules.Host;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Collective extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Hivemind");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Hivemind");

        // Modules
        Modules.get().add(new Worker());
        Modules.get().add(new Host());
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
        return new GithubRepo("machiecodes", "collective-addon");
    }
}
