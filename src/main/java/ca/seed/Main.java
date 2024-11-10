package ca.seed;

import ca.seed.client.Seed;
import ca.seed.server.SeedServer;

public final class Main {
    public static void main(String[] args) {
        Seed seed = Seed.getInstance();
        Thread renderLoop = new Thread(seed, "Render Thread");
        renderLoop.start();
        SeedServer seedServer = SeedServer.getInstance();
        Thread gameLoop = new Thread(seedServer, "Game Thread");
        gameLoop.start();
    }
}
