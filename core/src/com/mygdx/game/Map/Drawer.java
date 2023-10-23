package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

import java.util.Set;

public class Drawer {
    private final static Texture greyPixel = new Texture("GreyPixel.jpg");
    private final static Texture losePicture = new Texture("JumpScare.jpg");
    private final static Texture funnyLosePicture = new Texture("ButtonEnter.jpg");
    private final static Texture blackPixel = new Texture("BlackLargePixel.jpg");
    private final static Texture lox = new Texture("Lox.png");

    void drawMap(Set<Shape> shapes, SpriteBatch batch) {
        drawShapes(shapes, batch);

        for (int x = 0; x <= InGameMap.mapSizeX; x += 50) {
            for (int y = 0; y <= InGameMap.mapSizeY; y++) {
                batch.draw(greyPixel, x, y);
            }
        }
    }

    void drawLose(SpriteBatch batch) {
        batch.draw(losePicture, 0, InGameMap.mapSizeY - 850);
        batch.draw(lox, 530, 720);
    }

    void drawButton(SpriteBatch batch) {
        batch.draw(funnyLosePicture, 100, 50, 300, 100);
    }

    void drawShapes(Set<Shape> shapes, SpriteBatch batch) {
        for (Shape shape : shapes) {
            for (SoloBlock block : shape.getBlocks()) {
                batch.draw(block.texture, block.x, block.y, SoloBlock.size, SoloBlock.size);
            }
        }
    }

    void drawLinesInCorner(SpriteBatch batch){
        int startX = InGameMap.mapSizeX;
        int endX = ScoreMap.sizeX;
        int startY = ScoreMap.sizeY - 250;
        int endY = ScoreMap.sizeY;
        int lineSize = 20;

        for (int y = startY; y <= endY; y += 5) {
            for (int x = startX; x <= endX; x += 5) {
                if ((x > startX && x < endX) && (y > startY && y < endY)) {
                    continue;
                }
                batch.draw(blackPixel, x, y, lineSize, lineSize);
            }
        }
    }

    void drawNextShape(Shape shape, SpriteBatch batch) {
        int addXForNextShape = InGameMap.mapSizeX - shape.getStartX() + 5 * SoloBlock.size;

        for (SoloBlock block : shape.getBlocks()) {
            batch.draw(block.texture, block.x + addXForNextShape
                    ,block.y - SoloBlock.size, SoloBlock.size, SoloBlock.size);
        }
    }

}
