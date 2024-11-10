package ca.seed.client.asset.shader;

import ca.seed.client.asset.AssetTypes;

public final class Shaders {
    public static final Shader GALAXY_SHADER = Shaders.create("galaxy_shader", "world_vertex_shader", "world_fragment_shader");
    public static final Shader GUI_SHADER = Shaders.create("gui_shader", "gui_vertex_shader", "gui_fragment_shader");
    public static final Shader WORLD_SHADER = Shaders.create("world_shader", "world_vertex_shader", "world_fragment_shader");

    private static Shader create(String name, String vertexShader, String fragmentShader) {
        return AssetTypes.SHADERS.create(name, new Shader(vertexShader, fragmentShader));
    }

    public static void init() {
        AssetTypes.SHADERS.forEach(Shader::compileAndLink);
    }
}
