package io;

import collidables.Block;

import java.awt.Color;
import java.util.TreeMap;

/**
 * this is the blockfactory map class.
 */
public class BlockFactoryByMap implements BlockCreator {
    //Members.
    private String symbol;
    private int width;
    private int height;
    private int hitPoints;
    private Color stroke;
    private TreeMap<Integer, String> fillMap;

    /**
     * Constructor.
     *
     * @param symbol symbol.
     * @param map    map value.
     */
    public BlockFactoryByMap(String symbol, TreeMap<String, String> map) {
        this.symbol = symbol;
        this.fillMap = new TreeMap<Integer, String>();
        this.stroke = null;
        if (map.containsKey("height")) {
            this.height = Integer.parseInt(map.get("height"));
        }
        if (map.containsKey("width")) {
            this.width = Integer.parseInt(map.get("width"));
        }
        if (map.containsKey("stroke")) {
            String s = map.get("stroke");
            io.Background background = new io.Background();
            this.stroke = background.getColor(s);
        }

        if (map.containsKey("hit_points")) {
            this.hitPoints = Integer.parseInt(map.get("hit_points"));
        }
        if (this.hitPoints > 1) {
            int i = 0;
            if (map.containsKey("fill") && !map.containsKey("fill-1")) {
                String string = map.get("fill");
                fillMap.put(1, string);
                i = 1;
            } else {
                i = 0;
            }
            for (; i < this.hitPoints; i++) {
                String key = "fill-" + Integer.toString(i + 1);
                if (map.containsKey(key)) {
                    String string = map.get(key);
                    fillMap.put((i + 1), string);
                }
            }
        }
        if (this.hitPoints == 1) {
            if (map.containsKey("fill")) {
                String string = map.get("fill");
                fillMap.put(1, string);
            } else if (map.containsKey("fill-1")) {
                String string = map.get("fill-1");
                fillMap.put(1, string);
            }
        }
    }

    /**
     * Create blocks.
     *
     * @param xpos x value.
     * @param ypos y value.
     * @return Block.
     */
    public Block create(int xpos, int ypos) {
        Block block;
        if (this.stroke == null) {
            block = new Block(xpos, ypos, width, height, fillMap);
        } else {
            block = new Block(xpos, ypos, width, height, fillMap, stroke);
        }
        block.setNumberOnBlock(this.hitPoints);
        return block;
    }
}

