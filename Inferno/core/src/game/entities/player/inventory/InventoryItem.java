package game.entities.player.inventory;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import items.Items;

public class InventoryItem extends Actor {
    private TextureRegion txt;
    public TextureRegion[] downTextures;
    public TextureRegion[] upTextures;
    public TextureRegion rightTxt;
    public TextureRegion leftTxt;
    private InventoryItemGroups group;

    // TODO: remove this constructor
    public InventoryItem(String itemId) {
        txt = Items.get(itemId).inventoryTxt;
        downTextures = Items.get(itemId).downTextures;
        upTextures = Items.get(itemId).upTextures;
        rightTxt = Items.get(itemId).rightTxt;
        leftTxt = Items.get(itemId).leftTxt;
        group = InventoryItemGroups.tool;
        setWidth(48);
        setHeight(48);
    }

    public InventoryItem(InventoryItemData item) {
        txt = item.getInventoryTxt();
        downTextures = item.getTextures();
        upTextures = item.getTextures();
        rightTxt = item.getInventoryTxt();
        leftTxt = item.getInventoryTxt();
        group = item.getGroup();
        setWidth(48);
        setHeight(48);
    }

    public InventoryItem(engine.items.Items.Config config) {
        txt = config.getTxt();
        downTextures = new TextureRegion[0];
        upTextures = new TextureRegion[0];
        rightTxt = new TextureRegion();
        leftTxt = new TextureRegion();
        group = InventoryItemGroups.tool;
        setWidth(48);
        setHeight(48);
    }

    public InventoryItemGroups getType() {
        return group;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(txt, getX(), getY(), InventoryConstants.slotSize, InventoryConstants.slotSize);
    }
}