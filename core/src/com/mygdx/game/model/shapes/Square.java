package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public class Square extends Shape {
    public Square(Texture texture, int startX) {
        super(texture, startX);
        id = nextId;

        blocks.add(new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size));

        axisRotationBlock = blocks.get(1);

        //[2][3]
        //[0][1]
    }

    @Override
    protected Shape getCopy() {
        Shape newShape = new Square(texture, startX);
        newShape.getBlocks().clear();
        for(SoloBlock block: blocks){
            newShape.getBlocks().add(block.getCopy());
        }

        newShape.axisRotationBlock = newShape.getBlocks().get(1);
        return newShape;
    }
}
