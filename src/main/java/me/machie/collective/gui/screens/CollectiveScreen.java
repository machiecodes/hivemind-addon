package me.machie.collective.gui.screens;

import me.machie.collective.settings.CSettings;
import me.machie.collective.settings.TSettings;
import me.machie.collective.systems.collective.CollectiveSystem;
import meteordevelopment.meteorclient.gui.GuiTheme;
import meteordevelopment.meteorclient.gui.tabs.Tab;
import meteordevelopment.meteorclient.gui.tabs.TabScreen;
import meteordevelopment.meteorclient.gui.widgets.containers.WContainer;
import meteordevelopment.meteorclient.gui.widgets.containers.WWindow;
import meteordevelopment.meteorclient.utils.misc.NbtUtils;

public class CollectiveScreen extends TabScreen {
    private WWindow cWindow;
    private WContainer cContainer;

    private WWindow tWindow;
    private WContainer tContainer;

    public CollectiveScreen(GuiTheme theme, Tab tab) {
        super(theme, tab);
    }

    @Override
    public void initWidgets() {
        cWindow = add(theme.window("Connections")).widget();
        cContainer = cWindow.add(theme.verticalList()).expandX().widget();
        cContainer.add(theme.settings(CSettings.get())).expandX();

        tWindow = add(theme.window("Task Menu")).widget();
        tContainer = tWindow.add(theme.verticalList()).expandX().widget();
        tContainer.add(theme.settings(TSettings.get())).expandX();
    }

    private void fillConnections(WWindow window) {

    }

    @Override
    public boolean toClipboard() {
        return NbtUtils.toClipboard(CollectiveSystem.get());
    }

    @Override
    public boolean fromClipboard() {
        return NbtUtils.toClipboard(CollectiveSystem.get());
    }

    @Override
    public void tick() {
        super.tick();

        CSettings.get().tick(cContainer, theme);
        TSettings.get().tick(tContainer, theme);
    }
}
