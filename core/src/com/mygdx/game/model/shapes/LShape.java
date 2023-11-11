package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public class LShape extends Shape{
    public LShape(Texture texture, int startX) {
        super(texture, startX);
        id = nextId;

        blocks.add(new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - 3 * SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - 3 * SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size));

        axisRotationBlock = blocks.get(2);

        //[3]
        //[2]
        //[1][0]
    }

    @Override
    protected Shape getCopy() {
        Shape newShape = new LShape(texture, startX);
        newShape.getBlocks().clear();
        for(SoloBlock block: blocks){
            newShape.getBlocks().add(block.getCopy());
        }

        newShape.axisRotationBlock = newShape.getBlocks().get(2);
        return newShape;
    }
}
