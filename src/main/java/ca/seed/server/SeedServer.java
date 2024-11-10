package ca.seed.server;

import ca.seed.server.attribute.Attributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeedServer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(SeedServer.class);

    private static SeedServer instance;

    public static SeedServer getInstance() {
        if (instance == null) {
            return instance = new SeedServer();
        }
        return instance;
    }

    @Override
    public void run() {
        LOGGER.info("Started Game Thread");

        Attributes.init();
    }
}
