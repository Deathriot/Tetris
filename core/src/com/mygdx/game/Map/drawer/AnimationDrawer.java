package com.mygdx.game.Map.drawer;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

import java.util.List;

public class AnimationDrawer {
    private static int frame = 0;
    private static Texture white;
    private static Texture grey;

    public static void init() {
        Pixmap pixmap = new Pixmap(1, SoloBlock.size, Pixmap.Format.RGB888);
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fillRectangle(0, 0, 1, SoloBlock.size);
        white = new Texture(pixmap);

        Pixmap greyPixel = new Pixmap(1, SoloBlock.size, Pixmap.Format.RGB888);
        greyPixel.setColor(0.8f, 0.8f, 0.8f, 0.95f);
        greyPixel.fillRectangle(0, 0, 1, SoloBlock.size);
        grey = new Texture(greyPixel);
    }

    public static boolean drawDestroyAnimation(List<Integer> lines, SpriteBatch batch, Shape currentShape) {
        drawShape(batch, currentShape);

        int middleX = InGameMap.mapSizeX / 2;
        int height = SoloBlock.size;
        frame += 5;
        int drawX = middleX - frame;

        for(Integer line : lines){
            batch.draw(white, drawX, line, frame * 2, height);

            for (int x = 0; x <= InGameMap.mapSizeX; x += 50) {
                for (int y = 0; y <= line + SoloBlock.size; y += 50) {
                    batch.draw(grey, x, y);
                }
            }
        }

        if (frame == InGameMap.mapSizeX / 2) {
            frame = 0;
            return false;
        }

        return true;
    }

    private static void drawShape(SpriteBatch batch, Shape shape) {
        for (SoloBlock block : shape.getBlocks()) {
            batch.draw(block.texture, block.x, block.y, SoloBlock.size, SoloBlock.size);
        }
    }
}
