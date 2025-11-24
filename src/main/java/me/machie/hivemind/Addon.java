package me.machie.hivemind;

import com.mojang.logging.LogUtils;
import me.machie.hivemind.modules.Hive;
import me.machie.hivemind.modules.Mind;
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
        Modules.get().add(new Hive());
        Modules.get().add(new Mind());
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
