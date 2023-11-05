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
}
