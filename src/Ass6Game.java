

import animation.Animation;
import animation.AnimationRunner;
import game.GameFlow;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import io.LevelSpecificationReader;
import scores.HighScoresAnimation;
import scores.HighScoresTable;



import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;
import menu.SubMenu;
import menu.Task;

import java.io.File;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


/**
 * Ass6Game. class for main.
 */
public class Ass6Game {
    /**
     * main.
     *
     * @param args path to level sets.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        DialogManager dialog = gui.getDialogManager();
        AnimationRunner ar = new AnimationRunner(gui, framesPerSecond, sleeper);
        KeyboardSensor ks = gui.getKeyboardSensor();
        Menu<Task<Void>> menu = new MenuAnimation(ks);
        String path = null;
        //Open level set.
        if (args.length > 0) {
            path = args[0];
        } else {
            path = "level_sets.txt";

        }
        while (true) {
            createGame(menu, path, ar, ks, dialog, gui);
            ar.run(menu);
            Task<Void> t = menu.getStatus();
            if (t != null) {
                t.run();
            }
            menu.cleanTasks();
        }
    }

    /**
     * this adds tasks to the menu.
     *
     * @param menu   main menu.
     * @param path   string of the path.
     * @param ar     animation runner.
     * @param ks     keyboard sensor.
     * @param dialog dialog manager.
     * @param gui    gui
     */
    public static void createGame(Menu<Task<Void>> menu, String path, AnimationRunner ar,
                                  KeyboardSensor ks, DialogManager dialog, GUI gui) {
        HighScoresTable highScoresTable = null;
        File highscoreFile = new File("highscores");
        if (highscoreFile.exists()) {
            highScoresTable = HighScoresTable.loadFromFile(highscoreFile);
        } else {
            highScoresTable = new HighScoresTable(5);
        }
        Animation highscore = new KeyPressStoppableAnimation(ks,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable, ks));
        HighScoresTable hst = highScoresTable;
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        Reader fileReader = levelSpecificationReader.fromFile(path);
        TreeMap<String, List<LevelInformation>> difLevelMap = levelSpecificationReader.getMap(fileReader);
        SubMenu<Void> subMenu = new SubMenu<Void>(ks, ar);
        Task<Void> back = new Task<Void>() {
            @Override
            public Void run() {
                return null;
            }
        };
        Set<String> mapKeys = difLevelMap.keySet();
        for (String s : mapKeys) {
            String[] split = s.split(":");
            String key = split[0].substring(1);
            subMenu.addSelection(key, split[1], new Task<Void>() {
                @Override
                public Void run() {
                    GameFlow game = new GameFlow(ar, ks, hst, highscoreFile, dialog, gui);
                    game.runLevels(difLevelMap.get(s));
                    return null;
                }
            });
        }
        Task<Void> highscores = new Task<Void>() {
            @Override
            public Void run() {
                ar.run(highscore);
                return null;
            }
        };
        Task<Void> exit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(1);
                return null;
            }
        };
        subMenu.addSelection("b", "Back", back);
        menu.addSelection("s", "Start Game", subMenu);
        menu.addSelection("h", "Highscores", highscores);
        menu.addSelection("q", "Quit", exit);
    }
}


