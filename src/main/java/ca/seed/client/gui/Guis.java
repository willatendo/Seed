package ca.seed.client.gui;

import ca.seed.client.gui.popups.ConsoleGui;
import ca.seed.client.gui.popups.OptionsGui;
import ca.seed.client.gui.popups.SettingsGui;

public final class Guis {
    public static final GuiType<ConsoleGui> CONSOLE_GUI = GuiType.create("console", new ConsoleGui());
    public static final GuiType<OptionsGui> EXIT_GUI = GuiType.create("exit", new OptionsGui());
    public static final GuiType<MainMenuGui> MAIN_MENU_GUI = GuiType.create("main_menu", new MainMenuGui());
    public static final GuiType<SettingsGui> SETTINGS_GUI = GuiType.create("settings", new SettingsGui());
}
