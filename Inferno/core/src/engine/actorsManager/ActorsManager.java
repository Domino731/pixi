package engine.actorsManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import engine.actors.DefaultActor;
import environment.trees.ExampleTree;
import game.entities.player.NewPlayer;
import player.TestObject;
import utils.Checkbox;

import java.util.ArrayList;
import java.util.Vector;

public class ActorsManager {
    private ArrayList<DefaultActor> allActors = new ArrayList<>();
    public NewPlayer player = new NewPlayer();
    private Vector<Checkbox> checkboxes = new Vector<>();
    private Vector<Checkbox> groundCheckboxes = new Vector<>();
    private TestObject testObject;

    public ActorsManager() {
        ExampleTree exampleTree = new ExampleTree();

        allActors.add(player);
        allActors.add(exampleTree);

        testObject = new TestObject();
        checkboxes.addAll(testObject.checkboxArrayList);
        checkboxes.addAll(player.getCheckboxArray());
        groundCheckboxes.add(exampleTree.getGroundCheckbox());
        groundCheckboxes.add(player.getGroundCheckbox());
    }

    public void draw(SpriteBatch sb) {
        for (DefaultActor actor : allActors) {
            actor.draw(sb);
        }
    }

    public void update() {
        player.setIsCollision(false);
        player.updatePos();

        // Loop to check collisions
        for (int i = 0; i < checkboxes.size(); i++) {
            Checkbox checkbox1 = checkboxes.get(i);
            for (int j = i + 1; j < checkboxes.size(); j++) {
                Checkbox checkbox2 = checkboxes.get(j);
                if (checkCollision(checkbox1, checkbox2)) {
                    player.setIsCollision(true);
                    player.resetPosition();
                }
            }
        }

        player.update();
    }

    public void renderCheckboxes(ShapeRenderer sr) {
        for (Checkbox cb : checkboxes) {
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(1, 0, 0, 1); // Red color
            sr.rect(cb.position.x, cb.position.y, cb.dim.width, cb.dim.height); // Draw the border of a rectangle at (100, 100) with width 200 and height 100
            sr.end();
        }
        for (Checkbox cb : groundCheckboxes) {
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(0, 0, 1, 1); // Red color
            sr.rect(cb.position.x, cb.position.y, cb.dim.width, cb.dim.height); // Draw the border of a rectangle at (100, 100) with width 200 and height 100
            sr.end();
        }
    }


    public static boolean checkCollision(Checkbox checkbox1, Checkbox checkbox2) {
        // Calculate the coordinates of the bounding boxes
        int x1 = checkbox1.position.x;
        int y1 = checkbox1.position.y;
        int width1 = checkbox1.dim.width;
        int height1 = checkbox1.dim.height;
        int x2 = checkbox2.position.x;
        int y2 = checkbox2.position.y;
        int width2 = checkbox2.dim.width;
        int height2 = checkbox2.dim.height;
        if (x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2) {
            return true;
        }
        return false;
    }
}
