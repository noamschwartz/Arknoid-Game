package collidables;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;
import io.Background;


import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Color color;
    private Rectangle rectangle;
    private int numOfHits;
    private List<HitListener> hitListeners;
    private Color stroke;
    private Image image;
    private String imagePath;
    private Map<Integer, String> fill;
    private Background background;

    /**
     * this is the contructor of the block class.
     *
     * @param rectangle    is the block as a rectangle.
     * @param colorOfBlock si the color of the block.
     */
    public Block(Rectangle rectangle, Color colorOfBlock) {
        this.rectangle = rectangle;
        this.color = colorOfBlock;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle    the rectangle
     * @param colorOfBlock the color of block
     * @param stroke       the stroke
     */
    public Block(Rectangle rectangle, Color colorOfBlock, Color stroke) {
        this.rectangle = rectangle;
        this.color = colorOfBlock;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }

    /**
     * Instantiates a new Block.
     *
     * @param colorOfBlock the color of block
     * @param upperleft    the upperleft
     * @param width        the width
     * @param height       the height
     * @param stroke       the stroke
     */
    public Block(Color colorOfBlock, Point upperleft, double width, double height, Color stroke) {
        this.rectangle = new Rectangle(upperleft, width, height);
        this.color = colorOfBlock;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }

    /**
     * Instantiates a new Block.
     *
     * @param x            the x
     * @param y            the y
     * @param width        the width
     * @param height       the height
     * @param colorOfBlock the color of block
     */
    public Block(double x, double y, double width, double height, Color colorOfBlock) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = colorOfBlock;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }

    /**
     * Instantiates a new Block.
     *
     * @param x            the x
     * @param y            the y
     * @param width        the width
     * @param height       the height
     * @param colorOfBlock the color of block
     * @param stroke       the stroke
     */
    public Block(double x, double y, double width, double height, Color colorOfBlock, Color stroke) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = colorOfBlock;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.background = null;
        this.stroke = null;
        this.color = null;
    }

    /**
     * Instantiates a new Block.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param fill   the fill
     * @param stroke the stroke
     */
    public Block(double x, double y, double width, double height, Map<Integer, String> fill, Color stroke) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.stroke = stroke;
        this.fill = fill;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
        this.image = null;
    }

    /**
     * Instantiates a new Block.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param fill   the fill
     */
    public Block(double x, double y, double width, double height, Map<Integer, String> fill) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.fill = fill;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = null;
        this.image = null;
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl is the listener to be added.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl is the listener to be removed.
     */

    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    /**
     * this it the hitter function. is creates a list of hit listeners and runs through the list of listeners.
     *
     * @param hitter is the ball hitting the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * this notifies the sprite that time has passed.
     *
     * @param dt is the dt.
     */
    public void timePassed(double dt) {

    }

    /**
     * this returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * this notifies the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  is the collision point with the object.
     * @param currentVelocity is the velocity in which the ball hits the object.
     * @param hitter          is the ball hits the object.   *
     * @return the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //boolean flag = false;
        //check if the change should bw made in the x axis
        if (this.rectangle.getUpperLeft().getX() == collisionPoint.getX() || this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth() == collisionPoint.getX()) {
            //change the dx value to negative.
            dx = -dx;
            //flag = true;
        }
        //check if the change should be made in the y axis.
        if (this.rectangle.getUpperLeft().getY() == collisionPoint.getY() || this.rectangle.getUpperLeft().getY()
                + this.rectangle.getHeight() == collisionPoint.getY()) {
            //change the dy value.
            dy = -dy;
            //  flag = true;
        }
        //if (flag) {
//            this.numOfHits= this.numOfHits - 1;

        this.notifyHit(hitter);
        //reduce the number of hits on the block.
        this.setNumberOnBlock(this.getNumOfHits() - 1);
        //return the new velocity.
        return new Velocity(dx, dy);
    }


    /**
     * this is in charge of drawing the block to the screen.
     *
     * @param surface is the surface to be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        if (this.fill != null && this.fill.containsKey(this.getNumOfHits())) {
            if (this.fill.get(this.getNumOfHits()).contains("color")) {
                this.drawColor(surface, this.fill.get(this.getNumOfHits()));
            }
            if (this.fill.get(this.getNumOfHits()).contains("image")) {
                this.drawImage(surface, this.fill.get(this.getNumOfHits()));
            }
        } else {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        }
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }

    }

    /**
     * draws the image to the block.
     *
     * @param surface   draw surface.
     * @param pathImage path to the image.
     */
    public void drawImage(DrawSurface surface, String pathImage) {
        String imageFinalPath = null;
        if (pathImage.startsWith("image(") && pathImage.endsWith(")")) {
            imageFinalPath = getString(pathImage, "image(", ")");
        }
        if (this.image != null && this.imagePath.equals(imageFinalPath)) {
            surface.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    image);
        } else {
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imageFinalPath);
                this.image = ImageIO.read(is);
                this.imagePath = imageFinalPath;
                surface.drawImage((int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        this.image);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * draws the block with the color.
     *
     * @param surface     draw surface.
     * @param colorString string of color name / rgb.
     */
    public void drawColor(DrawSurface surface, String colorString) {
        Background colorBackground = new Background();
        surface.setColor(colorBackground.getColor(colorString));
        //Draw the rectangle.
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }


    /**
     * Draw old color.
     *
     * @param d the d
     * @param r the r
     */
    public void drawOldColor(DrawSurface d, Rectangle r) {
        d.fillRectangle((int) r.getUpperLeft().getX(), (int) r.getUpperLeft().getY(),
                (int) r.getHeight(), (int) r.getWidth());
    }

    /**
     * this sets the number on the block.
     *
     * @param x is the value to be printed on the block.
     */
    public void setNumberOnBlock(int x) {
        //if the number on the block has reached 0
        if (x < 0) {
            x = 0;
        }
        this.numOfHits = x;
    }

    /**
     * this gets the number of hits on the block.
     *
     * @return the number of hits on the block.
     */
    public int getNumOfHits() {
        return this.numOfHits;
    }

    /**
     * this adds the block to the game as a collidable and as a sprite.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
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
