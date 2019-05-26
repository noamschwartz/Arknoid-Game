package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the sprites.Sprite collection class.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * this assigns the array list to sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * this adds the sprite to the array list.
     *
     * @param s is the sprite to be added.
     */
    public void addSprite(Sprite s) {

        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {

        this.sprites.remove(s);
    }

    /**
     * this calls timePassed() on all sprites.
     * @param dt is the speed
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> spritesRep = new ArrayList<>(sprites);
        for (Sprite sprite : spritesRep) {
            sprite.timePassed(dt);
        }
    }

    /**
     * this calls drawOn(d) on all sprites.
     *
     * @param d is the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}