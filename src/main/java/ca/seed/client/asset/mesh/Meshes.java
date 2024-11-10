package ca.seed.client.asset.mesh;

import ca.seed.client.asset.AssetTypes;
import ca.seed.client.resource.MeshLoader;

public final class Meshes {
    public static final Mesh LO = Meshes.create("lo", new float[]{
            // VO
            -0.5f, 0.5f, 0.5f,
            // V1
            -0.5f, -0.5f, 0.5f,
            // V2
            0.5f, -0.5f, 0.5f,
            // V3
            0.5f, 0.5f, 0.5f,
            // V4
            -0.5f, 0.5f, -0.5f,
            // V5
            0.5f, 0.5f, -0.5f,
            // V6
            -0.5f, -0.5f, -0.5f,
            // V7
            0.5f, -0.5f, -0.5f,}, new float[]{1F, 1F, 0F, 1F, 0F, 0F, 1F, 0F, 1F, 0F, 1F, 1F, 0F, 1F, 0F, 0F, 0F, 0F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F, 0F, 0F, 1F, 0F, 1F, 1F, 0F, 1F, 0F, 0F, 1F, 1F, 0F, 1F, 0F, 0F, 1F, 0F,}, new float[]{1F, 0F, 0F, -1F, 0F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 0F, 1F, 0F, 0F, -1F}, new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            4, 0, 3, 5, 4, 3,
            // Right face
            3, 2, 7, 5, 3, 7,
            // Left face
            6, 1, 0, 6, 0, 4,
            // Bottom face
            2, 1, 6, 2, 6, 7,
            // Back face
            7, 6, 4, 7, 4, 5,});

    private static Mesh create(String name, float[] vertexArray, float[] uvArray, float[] normalsArray, int[] elementArray) {
        return AssetTypes.MESHES.create(name, new Mesh(vertexArray, uvArray, normalsArray, elementArray));
    }

    private static Mesh create(String name) {
        return AssetTypes.MESHES.create(name, MeshLoader.loadMesh(name));
    }

    public static void init() {
    }
}
