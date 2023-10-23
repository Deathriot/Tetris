package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

// Фигура - колбаса
public class Line extends Shape {
    private boolean horizontal;
    public Line(Texture texture, int startX) {
        super(texture, startX);
        id = nextId;
        horizontal = true;

        blocks[0] = new SoloBlock(texture, startX - 4 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);
        blocks[1] = new SoloBlock(texture, startX - 3 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);
        blocks[2] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);
        blocks[3] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);

        // [0][1][2][3]
    }

    @Override
    protected Shape getMySelf() {
        return this;
    }

    @Override
    protected boolean isConnected(SoloBlock block) {
        if(block.y != blocks[0].y + SoloBlock.size){
            return false;
        }

        for(SoloBlock shapeBlock: blocks){
            if(block.x == shapeBlock.x){
                return true;
            }
        }
        return false;
    }

    @Override
    public void rotate() {
        if(horizontal){
            rotateHorizontal();
        } else {
            rotateVertical();
        }
        checkStop();
    }

    // Супер кривой поворот колбаски
    private void rotateHorizontal(){
        int x = 2;
        int y = 1;


        for(int i = 0; i < blocks.length; i++){
            blocks[i].x += x * SoloBlock.size;
            blocks[i].y += y * SoloBlock.size;

            if(!checkRotateIsCorrect()){
                blocks[i].x -= x * SoloBlock.size;
                blocks[i].y -= y * SoloBlock.size;
                return;
            }

            blocks[i].x -= x * SoloBlock.size;
            blocks[i].y -= y * SoloBlock.size;

            x--;
            y--;
        }

        x = 2;
        y = 1;

        for(int i = 0; i < blocks.length; i++){
            blocks[i].x += x * SoloBlock.size;
            blocks[i].y += y * SoloBlock.size;
            x--;
            y--;
        }

        horizontal = false;
    }

    private void rotateVertical(){
        int x = -2;
        int y = -1;

        for(int i = 0; i < blocks.length; i++){
            blocks[i].x += x * SoloBlock.size;
            blocks[i].y += y * SoloBlock.size;

            if(!checkRotateIsCorrect()){
                blocks[i].x -= x * SoloBlock.size;
                blocks[i].y -= y * SoloBlock.size;
                return;
            }
            blocks[i].x -= x * SoloBlock.size;
            blocks[i].y -= y * SoloBlock.size;

            x++;
            y++;
        }

        x = -2;
        y = -1;

        for(int i = 0; i < blocks.length; i++){
            blocks[i].x += x * SoloBlock.size;
            blocks[i].y += y * SoloBlock.size;
            x++;
            y++;
        }

        horizontal = true;
    }
}
