package ca.seed.client.asset.font;

import ca.seed.client.asset.texture.Texture;
import ca.seed.client.asset.texture.Textures;
import ca.seed.client.render.renderer.Renderer;
import ca.seed.client.render.renderer.font.FontDrawingInformation;
import ca.seed.client.resource.ResourceManager;
import ca.seed.utils.GsonHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public final class Font {
    private static final Logger LOGGER = LogManager.getLogger(Font.class);
    private final Map<String, CharacterInformation> characters = Maps.newHashMap();
    private final Map<String, Texture> textures = Maps.newHashMap();
    private final String name;

    public Font(String name) {
        this.name = name;
    }

    public void load() {
        JsonArray fontSheetArray = ResourceManager.getFont("seedy");
        for (String fontSheetPath : fontSheetArray.asList().stream().map(JsonElement::getAsString).toList()) {
            JsonObject fontSheetObject = ResourceManager.getFontSheet(this.name, fontSheetPath);
            Texture texture = Textures.create(GsonHelper.getString(fontSheetObject, "texture"));
            int textureWidth = GsonHelper.getIntegerOrDefault(fontSheetObject, "texture_width", 512);
            int textureHeight = GsonHelper.getIntegerOrDefault(fontSheetObject, "texture_height", 512);
            int characterSpriteWidth = GsonHelper.getIntegerOrDefault(fontSheetObject, "sprite_width", 16);
            int characterSpriteHeight = GsonHelper.getIntegerOrDefault(fontSheetObject, "sprite_height", 16);

            JsonArray characterArray = GsonHelper.getArray(fontSheetObject, "characters");
            List<JsonObject> characters = characterArray.asList().stream().map(JsonElement::getAsJsonObject).toList();

            for (int i = 0; i < characters.size(); i++) {
                JsonObject characterObject = characters.get(i);
                String characterUTF = GsonHelper.getString(characterObject, "id");
                int width = GsonHelper.getInteger(characterObject, "width");
                int height = GsonHelper.getInteger(characterObject, "height");
                int yOffset = GsonHelper.getIntegerOrDefault(characterObject, "y_offset", 0);

                int x = i % (textureWidth / characterSpriteWidth);
                int y = (int) Math.floor((double) i / ((double) textureHeight / characterSpriteHeight));
                float[] uv = Renderer.createUV(textureWidth, textureHeight, width, height, (characterSpriteWidth * x), (characterSpriteHeight - height) + (characterSpriteHeight * y));

                LOGGER.info("Start '{}' {} {} {} {}", characterUTF, x, y, (characterSpriteWidth * x), (characterSpriteHeight * y));

                this.characters.put(characterUTF, new CharacterInformation(width, height, yOffset, uv));
                this.textures.put(characterUTF, texture);
            }
        }
    }

    public FontDrawingInformation setup(String message) {
        Map<Integer, CharacterInformation> allCharacterInformation = Maps.newHashMap();
        List<Texture> textures = Lists.newArrayList();
        char[] messageCharacters = message.toCharArray();
        int length = messageCharacters.length;
        int fullWidth = 0;
        int fullHeight = 0;

        for (int i = 0; i < length; i++) {
            char character = messageCharacters[i];
            CharacterInformation characterInformation = this.characters.get(character + "");
            Texture texture = this.textures.get(character + "");
            fullWidth += characterInformation.width();
            fullHeight += characterInformation.height();
            allCharacterInformation.put(i, characterInformation);
            if (!textures.contains(texture)) {
                textures.add(texture);
            }
        }

        return new FontDrawingInformation(allCharacterInformation, textures, length, fullWidth, fullHeight);
    }
}
