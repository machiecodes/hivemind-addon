package me.machie.collective;

import com.mojang.logging.LogUtils;
import me.machie.collective.gui.tabs.CollectiveTab;
import me.machie.collective.systems.collective.CollectiveSystem;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.gui.tabs.Tabs;
import meteordevelopment.meteorclient.systems.Systems;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Collective extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Collective");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Collective");

        Tabs.add(new CollectiveTab());
        Systems.add(new CollectiveSystem());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "me.machie.collective";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("machiecodes", "collective-addon");
    }
}
