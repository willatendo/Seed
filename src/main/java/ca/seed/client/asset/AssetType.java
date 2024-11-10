package ca.seed.client.asset;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

public final class AssetType<T> implements Iterable<T> {
    private static final Logger LOGGER = LogManager.getLogger(AssetType.class);
    private static final Map<String, AssetType<?>> ASSET_TYPES = Maps.newHashMap();

    private final Map<String, T> assets = Maps.newHashMap();
    private final String name;

    public static <T> AssetType<T> create(String name) {
        if (!ASSET_TYPES.containsKey(name)) {
            AssetType<T> assetType = new AssetType<>(name);
            ASSET_TYPES.put(name, assetType);
            return assetType;
        }
        LOGGER.error("Asset type '{}' already exists!", name);
        throw new Error();
    }

    private AssetType(String name) {
        this.name = name;
    }

    public T get(String name) {
        if (this.assets.containsKey(name)) {
            return this.assets.get(name);
        }
        LOGGER.error("Asset type '{}' does not contain value '{}'!", this.name, name);
        throw new Error();
    }

    public T create(String name, T value) {
        if (!this.assets.containsKey(name)) {
            this.assets.put(name, value);
            return value;
        }
        LOGGER.error("Asset type '{}' already has value '{}'!", this.name, name);
        throw new Error();
    }

    public boolean contains(String name) {
        return this.assets.containsKey(name);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return this.assets.values().iterator();
    }
}
