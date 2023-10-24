package com.mygdx.game.generator;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.shapes.*;
import org.w3c.dom.Text;

import java.util.Random;

public class ShapeGenerator {
    private static final Random RND = new Random();

    public Shape generateShape() {
        Texture texture = generateTexture();
        int startX = generateXPosition();

        return createRandomShape(texture, startX);
    }

    private Texture generateTexture() {
        int colourNumber = RND.nextInt(6);

        switch (colourNumber) {
            case 0:
                return new Texture("Tetris-Red.png");
            case 1:
                return new Texture("Tetris-Blue.png");
            case 2:
                return new Texture("Tetris-Green.png");
            case 3:
                return new Texture("Tetris-Orange.png");
            case 4:
                return new Texture("Tetris-Purpule.png");
            case 5:
                return new Texture("Tetris-Yellow.png");
            default:
                throw new RuntimeException("Ошибка в генераторе выдачи цвета");
        }
    }

    private int generateXPosition() {
        final int longestShapeLength = 200;
        final int bound = InGameMap.mapSizeX - longestShapeLength;

        int randomX = RND.nextInt(bound);
        randomX += 249;

        randomX = randomX - (randomX % 50);
        return randomX;
    }

    private Shape createRandomShape(Texture texture, int startX) {
        int numberShape = RND.nextInt(6);

        switch (numberShape) {
            case 0:
                return new Line(texture, startX);
            case 1:
                return new Square(texture, startX);
            case 2:
                return new ZetShape(texture, startX);
            case 3:
                return new LShape(texture, startX);
            case 4:
                return new JShape(texture, startX);
            case 5:
                return new TShape(texture, startX);
            default:
                throw new RuntimeException("Сломан генератор выдачи фигур");
        }
    }
}
