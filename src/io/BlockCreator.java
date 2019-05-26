package io;


import collidables.Block;

/**
 * BlockCreator interface.
 */
public interface BlockCreator {
    /**
     * Create a block.
     *
     * @param xpos x value.
     * @param ypos y value.
     * @return Block.
     */
    Block create(int xpos, int ypos);
}