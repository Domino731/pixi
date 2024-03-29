package constants.actors.groundItem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import engine.fonts.actionCollision.ActionCollision;
import engine.fonts.actionCollision.ActionTypes;
import utils.Action;
import utils.vectors.DimensionVector;
import utils.vectors.Vector;

public class GroundItem {
    private Vector<Integer> position;
    private TextureRegion txt;
    private boolean isAvailableToPick = false;
    private ActionCollision actionCollision;

    public GroundItem(int positionX, int positionY, TextureRegion txt, engine.utils.Action action) {
        position = new Vector<>(positionX, positionY);
        this.txt = txt;
        actionCollision = createActionCollision(action);
    }

    public GroundItem(int positionX, int positionY, TextureRegion txt) {
        position = new Vector<>(positionX, positionY);
        this.txt = txt;
    }

    public void setActionCollision(engine.utils.Action action) {
        actionCollision = createActionCollision(action);
    }

    public void update(double deltaTime) {
    }

    public void draw(SpriteBatch sb) {
        sb.draw(txt, position.x, position.y, GroundItemConstants.renderSize, GroundItemConstants.renderSize);
    }

    private ActionCollision createActionCollision(final engine.utils.Action action) {
        return new ActionCollision(
                ActionTypes.ACTION_AREA,
                "",
                new Vector<>(position.x, position.y),
                new DimensionVector<>((int) GroundItemConstants.renderSize, (int) GroundItemConstants.renderSize),
                new Vector<>(0, 0),
                new Action() {
                    @Override
                    public void action() {
                        action.action();
                    }
                }
        );
    }


    // GETTERS //
    public ActionCollision getActionCollision() {
        return actionCollision;
    }
}
