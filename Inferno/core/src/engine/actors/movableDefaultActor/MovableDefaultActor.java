package engine.actors.movableDefaultActor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import engine.actors.DefaultActor;
import engine.actors.constants.ActorTypes;
import engine.actors.utils.ActorDirection;
import spritesManager.SpritesManager;
import utils.Checkbox;
import utils.TextureData;
import utils.vectors.DimensionCordVector;
import utils.vectors.DimensionVector;
import utils.vectors.Vector;

import java.util.ArrayList;

public class MovableDefaultActor extends DefaultActor {
    protected boolean isMoving = false;
    protected ActorDirection direction = new ActorDirection();
    protected byte speed = 1;
    private TextureRegion[][] textureRegions;
    private TextureData textureData;
    protected int aniTick, aniIndex, aniSpeed = 25;
    public Vector<Integer> finalPosition;
    protected int actionIndex;

    public MovableDefaultActor(int positionX, int positionY, ArrayList<Checkbox> checkboxArray, String texturePath, TextureData textureData, DimensionVector<Integer> dim, DimensionCordVector groundCheckbox) {
        super(ActorTypes.DYNAMIC, new Vector(positionX, positionY), checkboxArray, texturePath, dim, groundCheckbox);
        this.textureData = textureData;
        this.finalPosition = new Vector<>(positionX, positionY);
        loadAnimations();
    }

    private void loadAnimations() {
        short texturesInColumn = textureData.getTexturesInColumn();
        short texturesInRow = textureData.getTexturesInRow();
        short textureWidth = textureData.getTextureWidth();
        short textureHeight = textureData.getTextureHeight();
        Texture texture = SpritesManager.loadSprite(textureData.getSrc());
        textureRegions = new TextureRegion[texturesInColumn][texturesInRow];

        for (int j = 0; j < textureRegions.length; j++)
            for (int i = 0; i < textureRegions[j].length; i++)
                textureRegions[j][i] = new TextureRegion(texture, i * textureWidth, j * textureHeight, textureWidth, textureHeight);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(textureRegions[actionIndex][aniIndex], finalPosition.x, finalPosition.y, dim.width, dim.height);
    }

    public void resetPosition() {
        position.x = finalPosition.x;
        position.y = finalPosition.y;
        checkboxArray.get(0).position.x = finalPosition.x;
        checkboxArray.get(0).position.y = finalPosition.y;
        groundCheckbox.position.x = finalPosition.x + groundCheckbox.absolutePosition.x;
        groundCheckbox.position.y = finalPosition.y + groundCheckbox.absolutePosition.y;

    }

    /**
     * set aniTack & aniIndex to 0
     */
    protected void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    // setters
    public void setRight(boolean v) {
        direction.right = v;
    }

    public void setLeft(boolean v) {
        direction.left = v;
    }

    public void setTop(boolean v) {
        direction.top = v;
    }

    public void setBot(boolean v) {
        direction.bot = v;
    }


}
