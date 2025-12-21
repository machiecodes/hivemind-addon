package me.machie.collective.gui.tabs;

import me.machie.collective.gui.screens.CollectiveScreen;
import meteordevelopment.meteorclient.gui.GuiTheme;
import meteordevelopment.meteorclient.gui.tabs.Tab;
import meteordevelopment.meteorclient.gui.tabs.TabScreen;
import net.minecraft.client.gui.screen.Screen;

public class CollectiveTab extends Tab {
    public CollectiveTab() {
        super("Collective");
    }

    @Override
    public TabScreen createScreen(GuiTheme theme) {
        return new CollectiveScreen(theme, this);
    }

    @Override
    public boolean isScreen(Screen screen) {
        return screen instanceof CollectiveScreen;
    }
}
