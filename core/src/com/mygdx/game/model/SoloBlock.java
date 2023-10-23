package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

public class SoloBlock {
    public Texture texture;
    public final static int size = 50;
    public int x;
    public int y;

    public SoloBlock(Texture texture, int x, int y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }


    public boolean contains(SoloBlock block) {
        int tw = size;
        int th = size;
        int rw = size;
        int rh = size;

        int tx = this.x;
        int ty = this.y;
        int rx = block.x;
        int ry = block.y;

        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;

        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
}
