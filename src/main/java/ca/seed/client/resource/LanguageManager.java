package ca.seed.client.resource;

import ca.seed.client.asset.language.Language;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;

import java.util.Map;

public final class LanguageManager {
    private static final Map<String, String> TRANSLATIONS = Maps.newHashMap();

    public static String getTranslation(String key) {
        return TRANSLATIONS.getOrDefault(key, key);
    }

    public static void loadTranslations(Language language) {
        TRANSLATIONS.clear();
        JsonObject languageFile = ResourceManager.getLanguageFile(language.code());
        languageFile.asMap().forEach((key, value) -> TRANSLATIONS.put(key, value.getAsString()));
    }
}
