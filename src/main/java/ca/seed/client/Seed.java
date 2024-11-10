package ca.seed.client;

import ca.seed.client.asset.AssetTypes;
import ca.seed.client.gui.GuiManager;
import ca.seed.client.gui.Guis;
import ca.seed.client.render.GameRenderer;
import ca.seed.client.window.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Seed implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Seed.class);

    private static Seed instance;

    private final GuiManager guiManager = new GuiManager();
    private final Window window;
    private long lastTime;
    private int frames;
    public int fps;

    private volatile boolean running;

    public static Seed getInstance() {
        if (instance == null) {
            return instance = new Seed();
        }
        return instance;
    }

    public Seed() {
        this.window = Window.getInstance();

        this.running = true;
    }

    public GuiManager getGuiManager() {
        return this.guiManager;
    }

    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        LOGGER.info("Started Render Thread");

        GameRenderer gameRenderer = new GameRenderer();

        this.window.create(gameRenderer);

        AssetTypes.init();

        // this.guiManager.addGui(0, Guis.MAIN_MENU_GUI);

        gameRenderer.create();

        while (this.running) {
            if (this.window.shouldClose()) {
                this.stop();
            }

            this.frames++;
            this.window.updateDisplay(gameRenderer);

            while ((System.nanoTime() / 1000000L) >= this.lastTime + 1000L) {
                this.fps = this.frames;
                this.lastTime += 1000L;
                this.frames = 0;
            }
        }

        this.window.destroy();
    }
}
