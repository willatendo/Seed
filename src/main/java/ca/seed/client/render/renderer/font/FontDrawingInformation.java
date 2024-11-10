package ca.seed.client.render.renderer.font;

import ca.seed.client.asset.font.CharacterInformation;
import ca.seed.client.asset.texture.Texture;

import java.util.List;
import java.util.Map;

public record FontDrawingInformation(Map<Integer, CharacterInformation> allCharacterInformation, List<Texture> textures, int length, int fullWidth, int fullHeight) {
}
