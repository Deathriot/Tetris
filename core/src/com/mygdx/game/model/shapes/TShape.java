package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public class TShape extends Shape{

    public TShape(Texture texture, int startX) {
        super(texture, startX);
        id = nextId;

        blocks[0] = new SoloBlock(texture, startX - 3 * SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[1] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[2] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[3] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);

        axisRotationBlock = blocks[1];

        //   [3]
        //[0][1][2]
    }

    @Override
    protected Shape getCopy() {
        Shape newShape = new TShape(texture, startX);
        for(int i = 0; i < blocks.length; i++){
            newShape.getBlocks()[i] = blocks[i].getCopy();
        }

        newShape.axisRotationBlock = newShape.getBlocks()[2];
        return newShape;
    }
}
