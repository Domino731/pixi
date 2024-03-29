package game.player.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import constants.Urls;
import game.player.Player;
import game.player.PlayerConstants;
import game.player.animations.config.ToolAnimationConfig;
import game.player.animations.config.WeaponAnimationConfig;
import game.player.playerTextures.CreateHairYOffset;
import game.player.playerTextures.PlayerTextures;
import game.player.style.data.PlayerHairsData;
import items.Items;
import utils.Direction;
import utils.vectors.Vector;

import static game.player.animations.config.AniConfigConstants.*;

public class PlayerAnimations {
    private Player player;
    private AnimationDraw[] animationDraws;
    private Vector<Integer> finalPosition;
    TextureRegion[][] bodyTextures, armsTextures, pantsTextures;
    TextureRegion weaponTexture = loadWeaponTexture();
    TextureRegion weaponTextureReversed = loadWeaponTextureReversed();
    TextureRegion[][] toolTxts = Items.get("stone_pickaxe").txts;
    public static final short[][][] weaponAnimations = WeaponAnimationConfig.animations;
    public static final byte[][][] toolAnimations = ToolAnimationConfig.animations;
    public short[][] bodyOffsets;
    public short[][] hatYOffsets;
    public byte[][] hatXOffsets;
    public short[][] shirtOffsets;
    public byte hairBaseOffset = 13;

    public final byte weaponXOrigin = 16 / 2;
    public final byte weaponYOrigin = 16 / 2;
    public Integer[][] hairOffset;
    public Integer[][] shirtOffset;

    public PlayerAnimations(Player player) {
        this.player = player;
        finalPosition = player.finalPosition;
        bodyTextures = player.playerTextures.bodyTextures;
        armsTextures = player.playerTextures.armsTextures;
        pantsTextures = player.playerTextures.pantsTextures;
        hairOffset = CreateHairYOffset.createOffsetForHair(player.playerTextures.armsTextures);
        shirtOffset = CreateHairYOffset.create(player.playerTextures.pantsTextures);
        bodyOffsets = player.playerTextures.hairOffsets;
        hatYOffsets = player.playerTextures.hatsYOffsets;
        shirtOffsets = player.playerTextures.shirtOffsets;
        hatXOffsets = player.playerTextures.hatsXOffsets;

        setAnimationDraws();
    }

    private TextureRegion loadWeaponTexture() {
        Texture txt = new Texture(Urls.SPRITE_WEAPONS);
        return new TextureRegion(txt, 16 * 7, 16 * 5, 16, 16);
    }

    private TextureRegion loadWeaponTextureReversed() {
        Texture txt = new Texture(Urls.SPRITE_WEAPONS);
        TextureRegion txtRg = new TextureRegion(txt, 16 * 7, 16 * 5, 16, 16);
        txtRg.flip(true, false);
        return txtRg;
    }

    public void running(SpriteBatch sb) {
        sb.draw(bodyTextures[player.actionIndex][player.aniIndex], finalPosition.x, finalPosition.y, 16, 32);
        sb.draw(player.style.hairArray[player.getDirectionIndex()], finalPosition.x, finalPosition.y + bodyOffsets[player.actionIndex][player.aniIndex] - hairBaseOffset, PlayerHairsData.HAIR_SIZE.width, PlayerHairsData.HAIR_SIZE.height);
        sb.draw(player.style.hatsArray[player.getDirectionIndex()], finalPosition.x + hatXOffsets[player.actionIndex][player.aniIndex], finalPosition.y + hatYOffsets[player.actionIndex][player.aniIndex], PlayerTextures.hatSize, PlayerTextures.hatSize);
        sb.draw(pantsTextures[player.actionIndex][player.aniIndex], finalPosition.x, finalPosition.y, 16, 16);
        sb.draw(player.style.shirtsArray[player.getDirectionIndex()], finalPosition.x + 4,
                finalPosition.y + shirtOffsets[player.actionIndex][player.aniIndex]
                , PlayerConstants.shirtDim.width, PlayerConstants.shirtDim.height);
        sb.draw(armsTextures[player.actionIndex][player.aniIndex], finalPosition.x, finalPosition.y, 16, 32);
    }

    public void draw(SpriteBatch sb) {
        animationDraws[player.actionIndex].draw(sb);
    }

    private void setAnimationDraws() {
        AnimationDraw[] animations = new AnimationDraw[PlayerConstants.ANI_AMOUNT];
        // set easy animations by loop
        for (int i = 0; i <= 7; i++) {
            animations[i] = new AnimationDraw() {
                @Override
                public void draw(SpriteBatch sb) {
                    running(sb);
                }
            };
        }
        // HARVEST WEED
        animations[PlayerConstants.ANI_HARVEST_UP] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                harvestAnimation(sb, PlayerConstants.ANI_HARVEST_UP, Direction.up, weaponTexture);
            }
        };
        animations[PlayerConstants.ANI_HARVEST_RIGHT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                harvestAnimation(sb, PlayerConstants.ANI_HARVEST_RIGHT, Direction.right, weaponTexture);
            }
        };
        animations[PlayerConstants.ANI_HARVEST_DOWN] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                harvestAnimation(sb, PlayerConstants.ANI_HARVEST_DOWN, Direction.down, weaponTexture);
            }
        };
        animations[PlayerConstants.ANI_HARVEST_LEFT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                harvestAnimation(sb, PlayerConstants.ANI_HARVEST_LEFT, Direction.left, weaponTextureReversed);
            }
        };
        // MINE RESOURCES
        animations[PlayerConstants.ANI_MINE_UP] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                mineAnimation(sb, PlayerConstants.ANI_MINE_UP, Direction.up);
            }
        };
        animations[PlayerConstants.ANI_MINE_RIGHT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                mineAnimation(sb, PlayerConstants.ANI_MINE_RIGHT, Direction.right);
            }
        };
        animations[PlayerConstants.ANI_MINE_DOWN] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                mineAnimation(sb, PlayerConstants.ANI_MINE_DOWN, Direction.down);
            }
        };
        animations[PlayerConstants.ANI_MINE_LEFT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                mineAnimation(sb, PlayerConstants.ANI_MINE_LEFT, Direction.left);
            }
        };
        // IDLE ITEM
        animations[PlayerConstants.ANI_IDLE_ITEM_UP] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };
        animations[PlayerConstants.ANI_IDLE_ITEM_RIGHT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };
        animations[PlayerConstants.ANI_IDLE_ITEM_DOWN] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };
        animations[PlayerConstants.ANI_IDLE_ITEM_LEFT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };
        // RUNNING ITEM
        animations[PlayerConstants.ANI_RUNNING_ITEM_UP] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };
        animations[PlayerConstants.ANI_RUNNING_ITEM_RIGHT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };
        animations[PlayerConstants.ANI_RUNNING_ITEM_DOWN] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };
        animations[PlayerConstants.ANI_RUNNING_ITEM_LEFT] = new AnimationDraw() {
            @Override
            public void draw(SpriteBatch sb) {
                itemAnimation(sb);
            }
        };

        animationDraws = animations;
    }

    private void itemAnimation(SpriteBatch sb) {
        running(sb);
    }

    private void harvestAnimation(SpriteBatch sb, byte bodyTexturesIndex, byte directionIndex, TextureRegion weaponTexture) {
        if (weaponAnimations[directionIndex][player.aniIndex][3] == 0) {
            sb.draw(weaponTexture, (player.finalPosition.x) + weaponAnimations[directionIndex][player.aniIndex][xIndex], player.finalPosition.y + weaponAnimations[directionIndex][player.aniIndex][yIndex], weaponXOrigin, weaponYOrigin, 16, 16, 1, 1, weaponAnimations[directionIndex][player.aniIndex][rotateIndex]);
        }
        sb.draw(bodyTextures[bodyTexturesIndex][player.aniIndex], player.finalPosition.x, player.finalPosition.y, 16, 32);
        sb.draw(player.style.hairArray[player.getDirectionIndex()], finalPosition.x, finalPosition.y + bodyOffsets[player.actionIndex][player.aniIndex] - hairBaseOffset, PlayerHairsData.HAIR_SIZE.width, PlayerHairsData.HAIR_SIZE.height);
        sb.draw(player.style.hatsArray[player.getDirectionIndex()], finalPosition.x + hatXOffsets[player.actionIndex][player.aniIndex], finalPosition.y + hatYOffsets[player.actionIndex][player.aniIndex], PlayerTextures.hatSize, PlayerTextures.hatSize);
        sb.draw(pantsTextures[player.actionIndex][player.aniIndex], finalPosition.x, finalPosition.y, 16, 16);
        sb.draw(player.style.shirtsArray[player.getDirectionIndex()], finalPosition.x + 4,
                finalPosition.y + shirtOffsets[player.actionIndex][player.aniIndex]
                , PlayerConstants.shirtDim.width, PlayerConstants.shirtDim.height);
        sb.draw(armsTextures[bodyTexturesIndex][player.aniIndex], player.finalPosition.x, player.finalPosition.y, 16, 32);
        if (weaponAnimations[directionIndex][player.aniIndex][3] == 1) {
            sb.draw(weaponTexture, (player.finalPosition.x) + weaponAnimations[directionIndex][player.aniIndex][xIndex], player.finalPosition.y + weaponAnimations[directionIndex][player.aniIndex][yIndex], weaponXOrigin, weaponYOrigin, 16, 16, 1, 1, weaponAnimations[directionIndex][player.aniIndex][rotateIndex]);
        }
    }

    private void mineAnimation(SpriteBatch sb, byte bodyTexturesIndex, byte directionIndex) {
        byte xHairOffset = 0;
        byte xHatOffset = -2;
        if (player.actionIndex == PlayerConstants.ANI_MINE_RIGHT) {
            if (player.aniIndex == 0) {
                xHatOffset = -3;
                xHairOffset = -1;
            }
        }
        if (player.actionIndex == PlayerConstants.ANI_MINE_LEFT) {
            if (player.aniIndex == 0) {
                xHatOffset = -1;
                xHairOffset = 1;
            }
        }

        if (toolAnimations[directionIndex][player.aniIndex][zIndex] == 0) {
            sb.draw(toolTxts[directionIndex][toolAnimations[directionIndex][player.aniIndex][txtIndex]],
                    player.finalPosition.x + toolAnimations[directionIndex][player.aniIndex][xIndex],
                    player.finalPosition.y + toolAnimations[directionIndex][player.aniIndex][yIndex],
                    PlayerTextures.toolXOrigin, PlayerTextures.toolYOrigin,
                    Items.toolWidth, Items.toolHeight, 1, 1, toolAnimations[directionIndex][player.aniIndex][rotateIndex]);
        }

        sb.draw(bodyTextures[bodyTexturesIndex][player.aniIndex], player.finalPosition.x, player.finalPosition.y, 16, 32);
        sb.draw(player.style.hairArray[player.getDirectionIndex()], finalPosition.x + xHairOffset, finalPosition.y + bodyOffsets[player.actionIndex][player.aniIndex] - hairBaseOffset, PlayerHairsData.HAIR_SIZE.width, PlayerHairsData.HAIR_SIZE.height);
        sb.draw(player.style.hatsArray[player.getDirectionIndex()], finalPosition.x + xHatOffset, finalPosition.y + hatYOffsets[player.actionIndex][player.aniIndex], PlayerTextures.hatSize, PlayerTextures.hatSize);
        sb.draw(pantsTextures[player.actionIndex][player.aniIndex], finalPosition.x, finalPosition.y, 16, 16);
        sb.draw(player.style.shirtsArray[player.getDirectionIndex()], finalPosition.x + 4,
                finalPosition.y + shirtOffsets[player.actionIndex][player.aniIndex]
                , PlayerConstants.shirtDim.width, PlayerConstants.shirtDim.height);
        sb.draw(armsTextures[bodyTexturesIndex][player.aniIndex], player.finalPosition.x, player.finalPosition.y, 16, 32);

        if (toolAnimations[directionIndex][player.aniIndex][zIndex] == 1) {
            sb.draw(toolTxts[directionIndex][toolAnimations[directionIndex][player.aniIndex][txtIndex]],
                    player.finalPosition.x + toolAnimations[directionIndex][player.aniIndex][xIndex],
                    player.finalPosition.y + toolAnimations[directionIndex][player.aniIndex][yIndex],
                    PlayerTextures.toolXOrigin, PlayerTextures.toolYOrigin,
                    Items.toolWidth, Items.toolHeight, 1, 1, toolAnimations[directionIndex][player.aniIndex][rotateIndex]);
        }
    }
}
