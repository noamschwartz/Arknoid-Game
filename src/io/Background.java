package io;

import biuoop.DrawSurface;
import sprites.Sprite;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * The type Background.
 */
public class Background {

    /**
     * Gets background.
     *
     * @param backgroundString the background string
     * @return the background
     */
    public Sprite getBackground(String backgroundString) {
        int flag = 0;
        Image img = null;
        Color backgroundColor = null;
        String imageFinalPath = null;
        if (backgroundString.startsWith("image(") && backgroundString.endsWith(")")) {
            imageFinalPath = getString(backgroundString, "image(", ")");
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(imageFinalPath);
            try {
                img = ImageIO.read(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = 1;
        } else {
            backgroundColor = getColor(backgroundString);
        }
        Image backgroundFinalImg = img;
        int isFlag = flag;
        Color backgroundFinalColor = backgroundColor;

        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                if (isFlag == 0) {
                    d.setColor(backgroundFinalColor);
                    d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                } else {
                    d.drawImage(0, 0, backgroundFinalImg);
                }
            }

            @Override
            public void timePassed(double dt) {

            }
        };
    }

    /**
     * Gets color.
     *
     * @param name the name
     * @return the color
     */
    public Color getColor(String name) {
        name = name.toLowerCase();
        if (name.contains("rgb")) {
            name = name.replace("color(rgb(", "");
            name = name.replace("))", "");
            String[] colors = name.split(",");
            int r = Integer.parseInt(colors[0]);
            int g = Integer.parseInt(colors[1]);
            int b = Integer.parseInt(colors[2]);
            return new Color(r, g, b);
        } else {
            if (name.startsWith("color(") && name.endsWith(")")) {
                String colorName = getString(name, "color(", ")");

                try {
                    if (colorName.equals("lightgray") || colorName.equals("lightGray")) {
                        return Color.LIGHT_GRAY;
                    }
                    Field field = Class.forName("java.awt.Color").getField(colorName);
                    return (Color) field.get(null);
                } catch (Exception e) {
                    throw new RuntimeException("unknown color: " + colorName);
                }
            }


        }
        return null;
    }

    /**
     * this is the return string function. it returns a cut string.
     *
     * @param string the tring to cut.
     * @param start  the start of the string.
     * @param end    the end of the string.
     * @return the new cut string.
     */
    private static String getString(String string, String start, String end) {
        return string.substring(start.length(), string.length() - end.length());
    }
}
