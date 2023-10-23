package com.mygdx.game.model.shapes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public class ZetShape extends Shape{

    private boolean horizontal;
    public ZetShape(Texture texture, int startX) {
        super(texture, startX);

        id = nextId;
        horizontal = true;

        blocks[0] = new SoloBlock(texture, startX - 3 * SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[1] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - 2 * SoloBlock.size);
        blocks[2] = new SoloBlock(texture, startX - 2 * SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);
        blocks[3] = new SoloBlock(texture, startX - SoloBlock.size, InGameMap.mapSizeY - SoloBlock.size);

        //   [2][3]
        //[0][1]
    }

    @Override
    protected Shape getMySelf() {
        return this;
    }

    @Override
    protected boolean isConnected(SoloBlock block) {
        for(SoloBlock myBlock: blocks){
            if(block.y == myBlock.y + SoloBlock.size && block.x == myBlock.x){
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
    }

    private void rotateHorizontal(){
        blocks[2].x += SoloBlock.size;
        blocks[2].y -= SoloBlock.size;

        //blocks[3].x += 0
        blocks[3].y -= 2 * SoloBlock.size;

        if(!checkRotateIsCorrect()){
            blocks[2].x -= SoloBlock.size;
            blocks[2].y += SoloBlock.size;

            blocks[3].y += 2 * SoloBlock.size;
            return;
        }

        blocks[0].x += SoloBlock.size;
        blocks[0].y += SoloBlock.size;

        //blocks[1].x += 0;
        //blocks[1].y += 0;

        horizontal = false;
    }

    private void rotateVertical(){
        blocks[0].x -= SoloBlock.size;
        blocks[0].y -= SoloBlock.size;

        //blocks[3].x += 0
        blocks[3].y += 2 * SoloBlock.size;

        if(!checkRotateIsCorrect()){
            blocks[0].x += SoloBlock.size;
            blocks[0].y += SoloBlock.size;

            blocks[3].y -= 2 * SoloBlock.size;
            return;
        }

        //blocks[1].x += 0;
        //blocks[1].y += 0;

        blocks[2].x -= SoloBlock.size;
        blocks[2].y += SoloBlock.size;

        horizontal = true;
    }
}

