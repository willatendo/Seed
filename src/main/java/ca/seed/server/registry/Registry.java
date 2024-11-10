package ca.seed.server.registry;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Registry<T> {
    private static final Logger LOGGER = LogManager.getLogger(Registry.class);

    private final Map<String, T> values = Maps.newHashMap();
    private final String name;

    public Registry(String name) {
        this.name = name;
        Registries.register(name, this);
    }

    public T register(String name, T value) {
        if (!this.values.containsKey(name)) {
            this.values.put(name, value);
            return value;
        }
        LOGGER.error("Name '{}' already exists in registry '{}'!", name, this.name);
        throw new Error();
    }
}
