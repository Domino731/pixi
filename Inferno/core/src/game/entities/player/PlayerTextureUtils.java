package game.entities.player;

public class PlayerTextureUtils {
    public static byte getHairYOffset(int playerAction, int aniIndex) {
        // handle idle state;
        if (playerAction == PlayerTextures.STATE_IDLE_UP || playerAction == PlayerTextures.STATE_IDLE_RIGHT || playerAction == PlayerTextures.STATE_IDLE_DOWN || playerAction == PlayerTextures.STATE_IDLE_LEFT) {
            return -3;
        }
        // handle running horizontally
        if (playerAction == PlayerTextures.STATE_RUNNING_LEFT || playerAction == PlayerTextures.STATE_RUNNING_RIGHT) {
            switch (aniIndex) {
                case 0:
                    return -4;
                case 1:
                    return -4;
                case 2:
                    return -3;
                case 3:
                    return -4;
                case 4:
                    return -4;
                case 5:
                    return -3;

            }
        }

        return 0;
    }
}
