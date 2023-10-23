package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public class Square extends Shape {
    public Square(Texture texture, int startX) {
        super(texture, startX);
        id = nextId;

        blocks[0] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[1] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[2] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);
        blocks[3] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);

        //[2][3]
        //[0][1]
    }

    @Override
    protected Shape getMySelf() {
        return this;
    }
    @Override
    protected boolean isConnected(SoloBlock block) {
        if (block.y != blocks[3].y + SoloBlock.size) {
            return false;
        }

        for (SoloBlock shapeBlock : blocks) {
            if (block.x == shapeBlock.x) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void rotate() {
        // nothing, can't rotate
    }
}
