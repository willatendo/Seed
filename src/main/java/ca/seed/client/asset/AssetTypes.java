package ca.seed.client.asset;

import ca.seed.client.asset.font.Font;
import ca.seed.client.asset.font.Fonts;
import ca.seed.client.asset.language.Language;
import ca.seed.client.asset.language.Languages;
import ca.seed.client.asset.mesh.Mesh;
import ca.seed.client.asset.mesh.Meshes;
import ca.seed.client.asset.shader.Shader;
import ca.seed.client.asset.shader.Shaders;
import ca.seed.client.asset.texture.Texture;
import ca.seed.client.asset.texture.Textures;
import ca.seed.client.resource.LanguageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AssetTypes {
    private static final Logger LOGGER = LogManager.getLogger(AssetTypes.class);

    public static final AssetType<Font> FONTS = AssetType.create("fonts");
    public static final AssetType<Language> LANGUAGES = AssetType.create("languages");
    public static final AssetType<Mesh> MESHES = AssetType.create("meshes");
    public static final AssetType<Shader> SHADERS = AssetType.create("shaders");
    public static final AssetType<Texture> TEXTURES = AssetType.create("textures");

    public static void init() {
        LOGGER.info("Started Loading Assets");

        LanguageManager.loadTranslations(Languages.US_ENGLISH);

        Fonts.init();
        Meshes.init();
        Shaders.init();
        Textures.init();

        LOGGER.info("Finished Loading Assets");
    }
}
