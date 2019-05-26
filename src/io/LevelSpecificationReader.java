package io;

import collidables.Block;
import levels.LevelInformation;
import sprites.Sprite;
import sprites.Velocity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {


    /**
     * From file reader.
     *
     * @param filepath the filepath.
     * @return the reader.
     */
    public Reader fromFile(String filepath) {
        Reader fileReader = null;
        File file = new File(filepath);
        try {
            if (file.exists()) {
                fileReader = new FileReader(filepath);
            } else {
                fileReader = new InputStreamReader(
                        ClassLoader.getSystemClassLoader().getResourceAsStream(filepath));
                return fileReader;
            }
        } catch (FileNotFoundException | NullPointerException ex) {
            throw new RuntimeException("File Not Found" + ex.toString());
        }
        return null;
    }

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelsInformation = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            String lineRead = bufferedReader.readLine().trim();
            while (lineRead != null) {
                if (lineRead.contains("#") || lineRead.equals("")) {
                    lineRead = bufferedReader.readLine().trim();
                    continue;
                }
                if (lineRead.startsWith("START_LEVEL")) {
                    List<TreeMap<String, String>> levelInfo = this.getInfoFromFile(bufferedReader, lineRead);
                    TreeMap<String, String> levelMap = levelInfo.get(0);
                    TreeMap<String, String> blocksMap = levelInfo.get(1);
                    LevelInformation levelInformation = makeLevelInfo(levelMap, blocksMap);
                    levelsInformation.add(levelInformation);
                    blocksMap.clear();
                    levelMap.clear();
                }
                lineRead = bufferedReader.readLine();
            }
            return levelsInformation;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return levelsInformation;
    }

    /**
     * this creates the level information.
     *
     * @param level  the level
     * @param blocks the blocks
     * @return the level information
     */
    public LevelInformation makeLevelInfo(TreeMap<String, String> level, TreeMap<String, String> blocks) {
        //Get name.
        String levelName = level.get("level_name");
        //Get velocities.
        List<Velocity> velocities = new ArrayList<Velocity>();
        String[] velocity = level.get("ball_velocities").split(" ");
        for (String v : velocity) {
            String[] angleAndSpeed = v.split(",");
            velocities.add(Velocity.fromAngleAndSpeed(Integer.parseInt(angleAndSpeed[0]),
                    Integer.parseInt(angleAndSpeed[1])));
        }
        // Get number of balls.
        int numOfBalls = velocities.size();
        //Get background.
        String backgroundString = level.get("background");
        Background colorBackground = new Background();
        Sprite background = colorBackground.getBackground(backgroundString);
        // Get paddle speed and width
        int paddleSpeed = Integer.parseInt(level.get("paddle_speed"));
        int paddleWidth = Integer.parseInt(level.get("paddle_width"));
        //Get block definitions.
        String blockDefinitions = level.get("block_definitions");
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefinitions);
        Reader reader = new InputStreamReader(inputStream);
        int blocksStartX = Integer.parseInt(level.get("blocks_start_x"));
        int blocksStartY = Integer.parseInt(level.get("blocks_start_y"));
        int rowHeight = Integer.parseInt(level.get("row_height"));
        int numOfBlocks = Integer.parseInt(level.get("num_blocks"));
        //Create blocks.
        BlocksFromSymbolsFactory blockFactory = BlockDefinitionReader.fromReader(reader);
        List<Block> blockList = this.fromMap(blocks, blocksStartX, blocksStartY, blockFactory, rowHeight);
        //Returns type of new LevelInformation.
        return new LevelInformation() {
            @Override
            public int numberOfBalls() {
                return numOfBalls;
            }

            @Override
            public List<Velocity> initialBallVelocities() {
                return velocities;
            }

            @Override
            public int paddleSpeed() {
                return paddleSpeed;
            }

            @Override
            public int paddleWidth() {
                return paddleWidth;
            }

            @Override
            public String levelName() {
                return levelName;
            }

            @Override
            public Sprite getBackground() {
                return background;
            }

            @Override
            public List<Block> blocks() {
                return blockList;
            }

            @Override
            public int numberOfBlocksToRemove() {
                return numOfBlocks;
            }
        };
    }

    /**
     * Gets info from the file.
     *
     * @param bufferedReader the buffered reader.
     * @param currentLine    the line being read.
     * @return the info from the file.
     */
//
    public List<TreeMap<String, String>> getInfoFromFile(BufferedReader bufferedReader, String currentLine) {
        TreeMap<String, String> levelMap = new TreeMap<String, String>();
        TreeMap<String, String> blocksMap = new TreeMap<String, String>();
        List<TreeMap<String, String>> list = new ArrayList<TreeMap<String, String>>();
        String line = currentLine;
        try {
            if (line.contains("START_LEVEL")) {
                while (!line.contains("END_LEVEL")) {
                    if (line.contains(":")) {
                        String[] keyValue = line.split(":");
                        levelMap.put(keyValue[0], keyValue[1]);
                    }
                    if (line.contains("START_BLOCKS")) {
                        line = bufferedReader.readLine().trim();
                        int i = 0;
                        while (!line.contains("END_BLOCKS")) {
                            String rowNum = null;
                            if (i > 9) {
                                rowNum = "row9" + Integer.toString(i);
                            } else {
                                rowNum = "row" + Integer.toString(i);
                            }
                            blocksMap.put(rowNum, line);
                            line = bufferedReader.readLine().trim();
                            i++;
                        }
                    }
                    line = bufferedReader.readLine().trim();

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        list.add(levelMap);
        list.add(blocksMap);
        return list;
    }

    /**
     * gets the list of blocks from the map.
     *
     * @param blocks  the blocks
     * @param x       x
     * @param y       y
     * @param factory the factory
     * @param height  the height
     * @return the list of blocks
     */

    public List<Block> fromMap(TreeMap<String, String> blocks, int x,
                               int y, BlocksFromSymbolsFactory factory, int height) {
        List<Block> blockList = new ArrayList<Block>();
        Set<String> rows = blocks.keySet();
        for (String row : rows) {
            String[] symbols = blocks.get(row).split("");
            int specificX = x;
            for (String symbol : symbols) {
                if (factory.isSpaceSymbol(symbol)) {
                    specificX = specificX + factory.getSpaceWidth(symbol);
                }
                if (factory.isBlockSymbol(symbol)) {
                    Block b = factory.getBlock(symbol, specificX, y);
                    specificX = specificX + (int) b.getCollisionRectangle().getWidth();
                    blockList.add(b);
                }
            }
            y = y + height;
        }
        return blockList;
    }

    /**
     * Gets map from the reader.
     *
     * @param reader the reader
     * @return the final map
     */
    public TreeMap<String, List<LevelInformation>> getMap(java.io.Reader reader) {
        TreeMap<String, List<LevelInformation>> levels = new TreeMap<>();
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                List<LevelInformation> value = new ArrayList<>();
                line = line.trim();
                String key = Integer.toString(i) + line;
                line = bufferedReader.readLine().trim();
                InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                value = levelSpecificationReader.fromReader(inputStreamReader);
                levels.put(key, value);
                line = bufferedReader.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levels;
    }

}


