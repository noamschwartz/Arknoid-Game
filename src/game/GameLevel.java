package game;



import animation.Animation;
import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.Collidable;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listeners.Counter;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.NameIndicator;
import sprites.Ball;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.SpriteCollection;
import sprites.Sprite;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;

import java.awt.Color;
import java.util.List;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the game class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballCounter;
    private Counter currentScore;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator liveIndicator;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private Paddle gamePaddle;
    private Ball[] balls;
    private NameIndicator nameIndicator;

    /**
     * this is the constructor of the game class.
     *
     * @param levelInformation the level information
     * @param keyboard         the keyboard
     * @param runner           the runner
     * @param currentScore     the current score
     * @param lives            the lives
     * @param liveIndicator    the live indicator
     * @param scoreIndicator   the score indicator
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter currentScore,
                     Counter lives, LivesIndicator liveIndicator, ScoreIndicator scoreIndicator) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInformation = levelInformation;
        this.blocksCounter = new Counter(this.levelInformation.numberOfBlocksToRemove());
        //Get the blocks.
        this.ballCounter = new Counter(0);
        this.currentScore = currentScore;
        this.scoreIndicator = scoreIndicator;
        this.currentScore = this.scoreIndicator.getCounter();
        this.lives = lives;
        this.liveIndicator = liveIndicator;
        this.lives = this.liveIndicator.getCounter();
        Sleeper sleeper = new Sleeper();
        this.runner = runner;
        this.nameIndicator = new NameIndicator(this.levelInformation.levelName());
        this.keyboard = keyboard;


    }

    /**
     * this returns the gui of the game.
     *
     * @return the gui of the game.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * this adds the collidable to the game envirenment.
     *
     * @param c is the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * this adds the sprite.
     *
     * @param s si the sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this initializes the game. it creates the perimeter blocks, 6 rows of blocks and a paddel.
     * it also creates two balls.
     */
    public void initialize() {

        this.sprites.addSprite(this.levelInformation.getBackground());
        List<Block> blocks = this.levelInformation.blocks();
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.currentScore);
        for (Block b : blocks) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }
        Block[] blocksAsBoundaries = this.createBlockFrame();
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), 800, 50), Color.LIGHT_GRAY);
        this.levelInformation.levelName();
        this.sprites.addSprite(scoreIndicator);
        this.sprites.addSprite(liveIndicator);
        this.sprites.addSprite(nameIndicator);
        Block deathBlock = this.createDeathBlock();

    }


    /**
     * this runs the game, it starts the animation loop.
     */
    public void playOneTurn() {
        this.running = true;
        this.balls = createBalls(this.levelInformation.numberOfBalls());
        Paddle paddle = new Paddle(this, new Point((400 - (this.levelInformation.paddleWidth() / 2)),
                600 - MGN.BLOCK_WIDTH + 25),
                this.levelInformation.paddleWidth(), 25, Color.ORANGE, this.keyboard);
        paddle.addToGame(this);
        this.gamePaddle = paddle;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
        gamePaddle.removeFromGame(this);
    }

    /**
     * this tells us if to stop or not.
     *
     * @return true of false.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @param d is the surface.
     * @param dt is the speed.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }

        if (this.blocksCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * this creates the frame blocks of the screen.
     *
     * @return the frame blocks.
     */
    public Block[] createBlockFrame() {
        //listeners.ScoreIndicator scoreIndicator = new listeners.ScoreIndicator(this.currentScore);
        Block[] blocks = new Block[3];
        blocks[0] = new Block(new Rectangle(new Point(0, 50), 800, 25),
                java.awt.Color.BLACK);
        blocks[1] = new Block(new Rectangle(new Point(0, 50), 25, 600),
                java.awt.Color.BLACK);
        blocks[2] = new Block(new Rectangle(new Point(800 - 25, 50),
                25, 600),
                java.awt.Color.BLACK);
        for (int i = 0; i < 3; i++) {
            blocks[i].addToGame(this);
        }
        return blocks;
    }

    /**
     * Create death block block.
     *
     * @return the block
     */
    public Block createDeathBlock() {
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        Block deathBlock = new Block(new Rectangle(new Point(25, 600), 750, 25), Color.BLUE);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
        return deathBlock;
    }


    /**
     * Create balls ball [ ].
     *
     * @param numOfBalls the num of balls
     * @return the ball [ ]
     */
    public Ball[] createBalls(int numOfBalls) {
        Ball[] ballsList = new Ball[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            ballsList[i] = new Ball(new Point(390, 570), 5, Color.WHITE);
            ballsList[i].setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ballsList[i].setBallGameEnvironment(this.environment);
            ballsList[i].addToGame(this);
            this.ballCounter.increase(1);
        }
        return ballsList;
    }

    /**
     * Gets num of blocks.
     *
     * @return the num of blocks.
     */
    public int getNumOfBlocks() {
        return this.blocksCounter.getValue();
    }
}