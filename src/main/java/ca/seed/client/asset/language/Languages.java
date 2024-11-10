package ca.seed.client.asset.language;

import ca.seed.client.asset.AssetTypes;

public final class Languages {
    public static final Language US_ENGLISH = Languages.create("English (US)", "en_us");

    public static Language create(String name, String code) {
        return AssetTypes.LANGUAGES.create(name, new Language(name, code));
    }
}
