package ca.seed.client.resource;

import ca.seed.client.asset.mesh.Mesh;
import ca.seed.client.asset.mesh.Meshes;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public final class MeshLoader {
    private static final Logger LOGGER = LogManager.getLogger(MeshLoader.class);

    public static Mesh loadMesh(String name) {
        List<VertexArray> vertexArrays = Lists.newArrayList();
        List<UVArray> uvArrays = Lists.newArrayList();
        List<NormalArray> normalArrays = Lists.newArrayList();
        List<Face> faces = Lists.newArrayList();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ResourceManager.getOBJ(name)));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\s+");
                switch (data[0]) {
                    case "v": {
                        vertexArrays.add(new VertexArray(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
                        break;
                    }
                    case "vt": {
                        uvArrays.add(new UVArray(Float.parseFloat(data[1]), Float.parseFloat(data[2])));
                        break;
                    }
                    case "vn": {
                        normalArrays.add(new NormalArray(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
                        break;
                    }
                    case "f": {
                        IndexGroup[] indexGroups = new IndexGroup[3];
                        for (int i = 0; i < 3; i++) {
                            String index = data[i + 1];
                            String[] indexData = index.split("/");
                            int length = indexData.length;
                            int pos = Integer.parseInt(indexData[0]) - 1;
                            int uv = -1;
                            int normal = -1;
                            if (length > 1) {
                                String uvData = indexData[1];
                                uv = !uvData.isEmpty() ? Integer.parseInt(uvData) - 1 : -1;
                                if (length > 2) {
                                    normal = Integer.parseInt(indexData[2]) - 1;
                                }
                            }
                            LogManager.getLogger().info("{} {} {}", pos, uv, normal);
                            indexGroups[i] = new IndexGroup(pos, uv, normal);
                        }
                        faces.add(new Face(indexGroups[0], indexGroups[1], indexGroups[2]));
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.info("Cannot find file '{}'!", name, e);
            throw new Error();
        }

        float[] vertexArray = new float[vertexArrays.size() * 3];
        float[] uvArray = new float[vertexArrays.size() * 2];
        float[] normalArray = new float[vertexArrays.size() * 3];
        List<Integer> indices = Lists.newArrayList();

        int i = 0;

        for (VertexArray data : vertexArrays) {
            vertexArray[i * 3] = data.x();
            vertexArray[i * 3 + 1] = data.y();
            vertexArray[i * 3 + 2] = data.z();
            i++;
        }

        for (Face face : faces) {
            IndexGroup[] indexGroups = face.indexGroups();
            for (IndexGroup indexGroup : indexGroups) {
                int pos = indexGroup.pos();
                indices.add(pos);
                if (indexGroup.uv >= 0) {
                    UVArray uvArrayData = uvArrays.get(indexGroup.uv);
                    uvArray[pos * 2] = uvArrayData.u();
                    uvArray[pos * 2 + 1] = 1 - uvArrayData.v();
                }
                if (indexGroup.normal >= 0) {
                    NormalArray normalArrayData = normalArrays.get(indexGroup.normal);
                    normalArray[pos * 3] = normalArrayData.x();
                    normalArray[pos * 3 + 1] = normalArrayData.y();
                    normalArray[pos * 3 + 2] = normalArrayData.z();
                }
            }
        }

        return new Mesh(vertexArray, uvArray, normalArray, indices.stream().mapToInt((Integer integer) -> integer).toArray());
    }

    private record VertexArray(float x, float y, float z) {
    }

    private record UVArray(float u, float v) {
    }

    private record NormalArray(float x, float y, float z) {
    }

    private record IndexGroup(int pos, int uv, int normal) {
    }

    private record Face(IndexGroup firstIndexGroup, IndexGroup secondIndexGroup, IndexGroup thirdIndexGroup) {
        public IndexGroup[] indexGroups() {
            return new IndexGroup[]{this.firstIndexGroup(), this.secondIndexGroup(), this.thirdIndexGroup()};
        }
    }
}
