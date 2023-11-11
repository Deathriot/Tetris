package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

import java.util.List;

// Фигура - колбаса
public class Line extends Shape {
    public Line(Texture texture, int startX) {
        super(texture, startX);
        id = nextId;

        blocks.add(new SoloBlock(texture, startX - 4 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - 3 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size));
        blocks.add(new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size));

        axisRotationBlock = blocks.get(1);

        // [0][1][2][3]
    }

    @Override
    protected Shape getCopy() {
        Shape newShape = new Line(texture, startX);
        newShape.getBlocks().clear();
        for(SoloBlock block: blocks){
            newShape.getBlocks().add(block.getCopy());
        }

        newShape.axisRotationBlock = newShape.getBlocks().get(1);
        return newShape;
    }
}
