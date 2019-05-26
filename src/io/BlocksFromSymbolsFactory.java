package io;

import collidables.Block;

import java.util.Map;

/**
 *this is the BlocksFromSymbolsFactory class.

 */
public class BlocksFromSymbolsFactory {
    //Members.
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor.
     *
     * @param space        map of spaces.
     * @param blockCreators map of blocks.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> space, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = space;
        this.blockCreators = blockCreators;
    }

    /**
     * isSpaceSymbol.
     *
     * @param string string.
     * @return returns true if the string is valid.
     */
    public boolean isSpaceSymbol(String string) {
        return this.spacerWidths.containsKey(string);
    }

    /**
     * getBlockSymbol.
     *
     * @param string string.
     * @return returns true if string is valid.
     */
    public boolean isBlockSymbol(String string) {
        return this.blockCreators.containsKey(string);
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param string symbol.
     * @param x x value.
     * @param y y value.
     * @return new Block.
     */
    public Block getBlock(String string, int x, int y) {
        return this.blockCreators.get(string).create(x, y);
    }

    /**
     * Returns the width space of the block.
     *
     * @param string symbol.
     * @return symbol space.
     */
    public int getSpaceWidth(String string) {
        return this.spacerWidths.get(string);
    }
}
