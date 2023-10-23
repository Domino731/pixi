package levelManager.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fasterxml.jackson.databind.JsonNode;
import utils.vectors.Vector2s;

import java.util.ArrayList;
import java.util.List;

public class Tiles {
    private final List<Tile> tilesList;

    public Tiles() {
        tilesList = new ArrayList<>();
    }

    public static final byte tileSize = 16;


    public void createTilesList(JsonNode tiles, int mapWidth, int mapHeight) {
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                boolean isExisting = false;
                JsonNode tile = null;
                for (JsonNode node : tiles) {
                    int nodeX = node.get("cords").get("x").asInt();
                    int nodeY = node.get("cords").get("y").asInt();
                    isExisting = nodeX == i && nodeY == j;
                    if (isExisting) {
                        tile = node;
                        break;
                    }
                }
                if (isExisting) {
                    // tile from map config
                    tilesList.add(new Tile(new Vector2s((short) i, (short) j), new Vector2s((short) tile.get("spriteCords").get("x").asInt(), (short) tile.get("spriteCords").get("y").asInt()), tile.get("spriteName").asText()));
                } else {
                    // default tile
                    tilesList.add(new Tile(new Vector2s((short) i, (short) j)));
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Tile tile : tilesList) {
            tile.draw(batch);
        }
    }
}
