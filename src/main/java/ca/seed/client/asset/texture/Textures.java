package ca.seed.client.asset.texture;

import ca.seed.client.asset.AssetTypes;

public final class Textures {
    public static final Texture MISSING = Textures.create("missing");

    // Editor
    public static final Texture CAKE_EDITOR_FLOOR = Textures.create("editor/cake_editor_floor");

    public static Texture create(String name) {
        return AssetTypes.TEXTURES.create(name, new Texture(name));
    }

    public static void init() {
        AssetTypes.TEXTURES.forEach(Texture::load);
    }
}
