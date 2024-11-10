package ca.seed.client.asset.font;

import ca.seed.client.asset.AssetTypes;

public final class Fonts {
    public static final Font SEEDY = Fonts.create("seedy");

    public static Font create(String name) {
        return AssetTypes.FONTS.create(name, new Font(name));
    }

    public static void init() {
        AssetTypes.FONTS.forEach(Font::load);
    }
}
