package ca.seed.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class ResourceManager {
    private static final Logger LOGGER = LogManager.getLogger(ResourceManager.class);
    private static final Gson GSON = new Gson();

    public static File get(String path, String extension) {
        return new File("src/main/resources/assets/seed/" + path + extension);
    }

    public static JsonArray getFont(String path) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(ResourceManager.get("fonts/" + path, ".json"));
        } catch (IOException e) {
            LOGGER.error("Cannot find font file '{}'", ResourceManager.get("fonts/" + path, ".json").getPath());
            throw new Error();
        }
        return GSON.fromJson(fileReader, JsonArray.class);
    }

    public static JsonObject getFontSheet(String fontName, String path) {
        FileReader fileReader = null;
        File file = ResourceManager.get("fonts/" + fontName + "/" + path, ".json");
        try {
            fileReader = new FileReader(file);
        } catch (IOException e) {
            LOGGER.error("Cannot find font sheet file '{}'", file.getPath());
            throw new Error();
        }
        return GSON.fromJson(fileReader, JsonObject.class);
    }

    public static JsonObject getLanguageFile(String code) {
        FileReader fileReader = null;
        File file = ResourceManager.get("lang/" + code, ".json");
        try {
            fileReader = new FileReader(file);
        } catch (IOException e) {
            LOGGER.error("Cannot find language file '{}'", file.getPath());
            throw new Error();
        }

        return GSON.fromJson(fileReader, JsonObject.class);
    }

    public static File getOBJ(String path) {
        return ResourceManager.get("models/" + path, ".obj");
    }

    public static File getShader(String path) {
        return ResourceManager.get("shaders/" + path, ".txt");
    }

    public static File getTexture(String path) {
        return ResourceManager.get("textures/" + path, ".png");
    }
}
