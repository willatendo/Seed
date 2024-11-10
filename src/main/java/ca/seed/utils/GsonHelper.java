package ca.seed.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GsonHelper {
    private static final Logger LOGGER = LogManager.getLogger(GsonHelper.class);

    public static int getInteger(JsonObject jsonObject, String key) {
        if (!jsonObject.has(key)) {
            LOGGER.error("Json object does not have key '{}'", key);
            throw new Error();
        }
        return jsonObject.get(key).getAsInt();
    }

    public static int getIntegerOrDefault(JsonObject jsonObject, String key, int defaultValue) {
        if (!jsonObject.has(key)) {
            return defaultValue;
        }
        return jsonObject.get(key).getAsInt();
    }

    public static String getString(JsonObject jsonObject, String key) {
        if (!jsonObject.has(key)) {
            LOGGER.error("Json object does not have key '{}'", key);
            throw new Error();
        }
        return jsonObject.get(key).getAsString();
    }

    public static JsonArray getArray(JsonObject jsonObject, String key) {
        if (!jsonObject.has(key)) {
            LOGGER.error("Json object does not have key '{}'", key);
            throw new Error();
        }
        return jsonObject.get(key).getAsJsonArray();
    }
}
