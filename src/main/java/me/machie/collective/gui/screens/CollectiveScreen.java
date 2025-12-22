package me.machie.collective.gui.screens;

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
        cWindow.id = "connections";
        cContainer = cWindow.add(theme.verticalList()).expandX().widget();
        // cContainer.add(theme.settings(ConnectionSettings.get())).expandX();

        tWindow = add(theme.window("Task Menu")).widget();
        tWindow.id = "taskmenu";
        tContainer = tWindow.add(theme.verticalList()).expandX().widget();
        // tContainer.add(theme.settings(TaskSettings.get())).expandX();
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

        // ConnectionSettings.get().tick(cContainer, theme);
        // TaskSettings.get().tick(tContainer, theme);
    }
}
