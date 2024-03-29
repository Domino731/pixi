package game.player.style.paintTextures;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static utils.TxtUtils.getPixmapFromTextureRegion;

public class Utils {
    public static HashMap<Integer, Integer> getColorsMapForSleeves(TextureRegion shirtTxtRg) {
        HashMap<Integer, Integer> newColors = new HashMap<>();
        newColors.put(1795177215, 1795177215);
        newColors.put(-106001921, -106001921);
        newColors.put(-529832449, -529832449);

        Pixmap pixmap = getPixmapFromTextureRegion(shirtTxtRg);

        int width = pixmap.getWidth();
        int height = pixmap.getHeight();

        ArrayList<Integer> colorsList = new ArrayList<>();
        int borderColor = 0;
        // Calculate the histogram of color occurrences within the TextureRegion
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixmap.getPixel(x, y);
                int r = (pixel & 0xff000000) >>> 24;
                int g = (pixel & 0x00ff0000) >>> 16;
                int b = (pixel & 0x0000ff00) >>> 8;
                int a = (pixel & 0x000000ff);

                // Ignore pixels that are completely transparent
                if (pixel != 0 && borderColor == 0) {
                    borderColor = pixel;
                }
                if (a != 0 && pixel != borderColor) {
                    colorsList.add(pixel);
                }
            }
        }

        // sorting by occurrences
        final Map<Integer, Integer> occurrenceMap = new HashMap<>();
        for (Integer number : colorsList) {
            occurrenceMap.put(number, occurrenceMap.getOrDefault(number, 0) + 1);
        }
        colorsList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return occurrenceMap.get(b) - occurrenceMap.get(a);
            }
        });

        // remove duplicates
        ArrayList<Integer> textureColors = new ArrayList<>();

        for (Integer color : colorsList) {
            if (!textureColors.contains(color)) {
                textureColors.add(color);
            }
        }

        // set main sleeve color
        newColors.put(-1910567681, textureColors.get(0));
        // set sleeve border color
        newColors.put(1242302207, borderColor);
        // set shadow color
        newColors.put(1880561919, textureColors.get(1));

        return newColors;
    }

    public static HashMap<Integer, Integer> getColorsMapForPants(TextureRegion shirtTxtRg) {
        HashMap<Integer, Integer> newColors = new HashMap<>();
        Pixmap pixmap = getPixmapFromTextureRegion(shirtTxtRg);
        int width = pixmap.getWidth();
        int height = pixmap.getHeight();
        ArrayList<Integer> colorsList = new ArrayList<>();
        int borderColor = 0;

        // Calculate the histogram of color occurrences within the TextureRegion
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixmap.getPixel(x, y);
                int r = (pixel & 0xff000000) >>> 24;
                int g = (pixel & 0x00ff0000) >>> 16;
                int b = (pixel & 0x0000ff00) >>> 8;
                int a = (pixel & 0x000000ff);

                // Ignore pixels that are completely transparent
                if (pixel != 0 && borderColor == 0) {
                    borderColor = pixel;
                }
                if (a != 0 && pixel != borderColor) {
                    colorsList.add(pixel);
                }
            }
        }

        // sorting by occurrences
        final Map<Integer, Integer> occurrenceMap = new HashMap<>();
        for (Integer number : colorsList) {
            occurrenceMap.put(number, occurrenceMap.getOrDefault(number, 0) + 1);
        }
        colorsList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return occurrenceMap.get(b) - occurrenceMap.get(a);
            }
        });

        // remove duplicates
        ArrayList<Integer> textureColors = new ArrayList<>();

        for (Integer color : colorsList) {
            if (!textureColors.contains(color)) {
                textureColors.add(color);
            }
        }
        //// 1. 707410431
        //// 2. 2038337279, 2054914815, 1852930559, -1869440513
        //// 3. -959851521, -1330331137
        //// 4. -131841

        // set main sleeve color
        newColors.put(-131841, textureColors.get(0));

        // set sleeve border color
        newColors.put(707410431, borderColor);
        newColors.put(976898815, borderColor);

        // set shadow color
        int shadowColor = textureColors.get(3);
        newColors.put(2038337279, shadowColor);
        newColors.put(2054914815, shadowColor);
        newColors.put(1852930559, shadowColor);
        newColors.put(-1869440513, shadowColor);

        // shadow 2
        int shadowColor2 = textureColors.get(1);
        newColors.put(-959851521, shadowColor2);

        return newColors;
    }

    public static TextureRegion getPaintedTexture(TextureRegion armTxtRg, HashMap<Integer, Integer> colorsMap) {
        Pixmap pixmap = getPixmapFromTextureRegion(armTxtRg);
        for (int x = 0; x < pixmap.getWidth(); x++) {
            for (int y = 0; y < pixmap.getHeight(); y++) {
                int colorInt = pixmap.getPixel(x, y);
                Integer color = colorsMap.get(colorInt);

                if (color != null) {
                    pixmap.drawPixel(x, y, color);
                } else {
                    pixmap.drawPixel(x, y, colorInt);
                }
            }
        }

        Texture newTxt = new Texture(pixmap);
        return new TextureRegion(newTxt);
    }
}
