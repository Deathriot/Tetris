package com.mygdx.game.Map.drawer;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.Map.ScoreMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

import java.util.Set;

public class Drawer {
    private final Texture greyPixel;
    private final static Texture losePicture = new Texture("endGame\\JumpScare.jpg");
    private final static Texture blackPixel = new Texture(new Pixmap(5, 5, Pixmap.Format.RGB888));
    private final static Texture lox = new Texture("endGame\\Lox.png");

    public Drawer() {
        Pixmap greyPixel = new Pixmap(1,1, Pixmap.Format.RGB888);
        greyPixel.setColor(0.8f,0.8f,0.8f,0.95f);
        greyPixel.fillRectangle(0,0,1,1);
        this.greyPixel = new Texture(greyPixel);
    }

    public void drawMap(Set<Shape> shapes, SpriteBatch batch) {
        drawShapes(shapes, batch);

        for (int x = 0; x <= InGameMap.mapSizeX; x += 50) {
            for (int y = 0; y <= InGameMap.mapSizeY; y++) {
                batch.draw(greyPixel, x, y);
            }
        }
    }

    public void drawLose(SpriteBatch batch) {
        batch.draw(losePicture, 0, InGameMap.mapSizeY - 850);
        batch.draw(lox, 530, 720);
    }
    public void drawShapes(Set<Shape> shapes, SpriteBatch batch) {
        for (Shape shape : shapes) {
            for (SoloBlock block : shape.getBlocks()) {
                if (block.x == -50) {
                    continue;
                }
                batch.draw(block.texture, block.x, block.y, SoloBlock.size, SoloBlock.size);
            }
        }
    }

    public void drawLinesInCorner(SpriteBatch batch) {
        int lineSize = 20;

        int startX = InGameMap.mapSizeX;
        int endX = ScoreMap.sizeX - lineSize;
        int startY = ScoreMap.sizeY - 250;
        int endY = ScoreMap.sizeY - lineSize;

        for (int y = startY; y <= endY; y += 5) {
            for (int x = startX; x <= endX; x += 5) {
                if ((x > startX && x < endX) && (y > startY && y < endY)) {
                    continue;
                }
                batch.draw(blackPixel, x, y, lineSize, lineSize);
            }
        }
    }

    public void drawNextShape(Shape shape, SpriteBatch batch) {
        int addXForNextShape = InGameMap.mapSizeX - shape.getStartX() + 5 * SoloBlock.size;

        for (SoloBlock block : shape.getBlocks()) {
            batch.draw(block.texture, block.x + addXForNextShape
                    , block.y - SoloBlock.size, SoloBlock.size, SoloBlock.size);
        }
    }
}
