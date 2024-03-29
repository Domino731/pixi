package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import engine.fonts.actionCollision.actorsManager.ActorsManager;
import game.player.Player;
import inputs.GameInputProcessor;
import utils.vectors.Vector;


public class PlayScreen implements Screen {
    private MyGdxGame game;
    private ActorsManager actorsManager;
    private Player player;
    Sprite sprite;
    OrthographicCamera camera;
    public static int cameraXOffset = 0;
    public static int cameraYOffset = 0;
    private static final float CAMERA_ZOOM = 0.2f;
    public static int PIXEL_WIDTH = (int) (1 / CAMERA_ZOOM);

    public PlayScreen(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cameraXOffset = (int) (camera.viewportWidth / 2);
        cameraYOffset = (int) (camera.viewportHeight / 2);
        camera.translate(cameraXOffset, cameraYOffset);

        actorsManager = new ActorsManager();
        player = actorsManager.player;
        Gdx.input.setInputProcessor(new GameInputProcessor(actorsManager));
        camera.zoom = CAMERA_ZOOM;
    }

    @Override
    public void show() {

    }

    private void update(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Vector<Integer> position = player.getPosition();
        camera.position.set(position.x, position.y, 0);
        camera.update();
        actorsManager.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        actorsManager.draw(game.batch);
        game.batch.end();


        player.inventory.draw();
        actorsManager.drawClock();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        sprite.getTexture().dispose();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
//        sprite.getTexture().dispose();
    }
}
