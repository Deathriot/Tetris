package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public class JShape extends Shape {
    private int rotatePosition = 1;
    public JShape(Texture texture, int startX) {
        super(texture, startX);
        id = nextId;

        blocks[0] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - 3 * SoloBlock.size);
        blocks[1] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - 3 * SoloBlock.size);
        blocks[2] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[3] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);

        //   [3]
        //   [2]
        //[0][1]
    }

    @Override
    protected Shape getMySelf() {
        return this;
    }

    @Override
    public void rotate() {
        switch (rotatePosition){
            case 1:
                rotate1();
                break;
            case 2:
                rotate2();
                break;
            case 3:
                rotate3();
                break;
            case 4:
                rotate4();
                break;
        }
    }

    private void rotate1(){
        blocks[0].y += 2 * SoloBlock.size;

        blocks[1].x -= SoloBlock.size;
        blocks[1].y += SoloBlock.size;

        blocks[3].x += SoloBlock.size;
        blocks[3].y -= SoloBlock.size;

        if(!checkRotateIsCorrect()){
            blocks[0].y -= 2 * SoloBlock.size;

            blocks[1].x += SoloBlock.size;
            blocks[1].y -= SoloBlock.size;

            blocks[3].x -= SoloBlock.size;
            blocks[3].y += SoloBlock.size;
            return;
        }
        rotatePosition = 2;
    }

    private void rotate2(){
        blocks[0].x += 2 * SoloBlock.size;

        blocks[1].x += SoloBlock.size;
        blocks[1].y += SoloBlock.size;

        blocks[3].x -= SoloBlock.size;
        blocks[3].y -= SoloBlock.size;

        if(!checkRotateIsCorrect()){
            blocks[0].x -= 2 * SoloBlock.size;

            blocks[1].x -= SoloBlock.size;
            blocks[1].y -= SoloBlock.size;

            blocks[3].x += SoloBlock.size;
            blocks[3].y += SoloBlock.size;
            return;
        }

        rotatePosition = 3;
    }

    private void rotate3(){
        blocks[0].y -= 2 * SoloBlock.size;

        blocks[1].x += SoloBlock.size;
        blocks[1].y -= SoloBlock.size;

        blocks[3].x -= SoloBlock.size;
        blocks[3].y += SoloBlock.size;

        if(!checkRotateIsCorrect()){
            blocks[0].y += 2 * SoloBlock.size;

            blocks[1].x -= SoloBlock.size;
            blocks[1].y += SoloBlock.size;

            blocks[3].x += SoloBlock.size;
            blocks[3].y -= SoloBlock.size;
            return;
        }
        rotatePosition = 4;
    }

    private void rotate4(){
        blocks[0].x -= 2 * SoloBlock.size;

        blocks[1].x -= SoloBlock.size;
        blocks[1].y -= SoloBlock.size;

        blocks[3].x += SoloBlock.size;
        blocks[3].y += SoloBlock.size;

        if(!checkRotateIsCorrect()){
            blocks[0].x += 2 * SoloBlock.size;

            blocks[1].x += SoloBlock.size;
            blocks[1].y += SoloBlock.size;

            blocks[3].x -= SoloBlock.size;
            blocks[3].y -= SoloBlock.size;
            return;
        }

        rotatePosition = 1;
    }
}
