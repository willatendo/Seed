package ca.seed.server.registry;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public final class Registries {
    private static final Map<String, Registry<?>> REGISTRIES = Maps.newHashMap();
    private static final Logger LOGGER = LogManager.getLogger(Registries.class);

    public static void register(String id, Registry<?> registry) {
        if (!REGISTRIES.containsKey(id)) {
            REGISTRIES.put(id, registry);
            return;
        }
        LOGGER.error("Registry '{}' already exists!", id);
        throw new Error();
    }
}
