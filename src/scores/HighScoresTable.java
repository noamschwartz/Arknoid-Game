package scores;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type High scores table. //
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> scoreTable;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreTable = new ArrayList<ScoreInfo>();

    }

    /**
     * Instantiates a new High scores table.
     */
    public HighScoresTable() {
        this.size = 5;
        this.scoreTable = new ArrayList<ScoreInfo>();

    }

    /**
     * Add a high score.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        this.scoreTable.add(score);
        for (int i = 0; i < this.scoreTable.size() - 1; i++) {
            for (int j = 0; j < (this.scoreTable.size() - i - 1); j++) {
                if (this.scoreTable.get(j).getScore() < this.scoreTable.get(j + 1).getScore()) {
                    ScoreInfo tempScore = this.scoreTable.get(j);
                    this.scoreTable.set(j, this.scoreTable.get(j + 1));
                    this.scoreTable.set((j + 1), tempScore);
                }
            }
        }
        if (this.scoreTable.size() > this.size()) {
            this.scoreTable.remove(this.size);
        }
    }

    /**
     * Size int.
     *
     * @return the table size.
     */

    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */

    public List<ScoreInfo> getHighScores() {
        for (int i = 0; i < this.scoreTable.size() - 1; i++) {
            for (int j = 0; j < (this.scoreTable.size() - i - 1); j++) {
                if (this.scoreTable.get(j).getScore() < this.scoreTable.get(j + 1).getScore()) {
                    ScoreInfo tempScore = this.scoreTable.get(j);
                    this.scoreTable.set(j, this.scoreTable.get(j + 1));
                    this.scoreTable.set((j + 1), tempScore);
                }
            }
        }
        return this.scoreTable;

    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */

    public int getRank(int score) {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> listReverse = new ArrayList<Integer>();
        if (this.scoreTable.size() == this.size()) {
            if (this.scoreTable.get(this.size() - 1).getScore() >= score) {
                return this.size + 1;
            }
        }
        for (ScoreInfo s : this.scoreTable) {
            list.add(s.getScore());
        }
        list.add(score);
        //Sort list.
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            listReverse.add(list.get(list.size() - 1 - i));
        }
        return listReverse.indexOf(score) + 1;

    }

    /**
     * Clear the table.
     */
    public void clear() {
        this.scoreTable.clear();
    }


    /**
     * Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */

    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(getHighScores());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (objectOutputStream != null) {
            objectOutputStream.close();
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable();
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            List<ScoreInfo> scoreInfos = (List<ScoreInfo>) objectInputStream.readObject();
            if (scoreInfos != null) {
                highScoresTable.clear();
                highScoresTable.getHighScores().addAll(scoreInfos);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.toString());
        } catch (ClassNotFoundException classExeption) {
            System.out.println(classExeption.toString());
        }
        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException ioCloseException) {
                System.out.println(ioCloseException.toString());
            }


        }
        return highScoresTable;
    }
}