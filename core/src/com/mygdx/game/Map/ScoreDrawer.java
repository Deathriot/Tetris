package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.SoloBlock;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ScoreDrawer {
    private final Texture one = new Texture("1.png");
    private final Texture two = new Texture("2.png");
    private final Texture three = new Texture("3.png");
    private final Texture four = new Texture("4.png");
    private final Texture five = new Texture("5.png");
    private final Texture six = new Texture("6.png");
    private final Texture seven = new Texture("7.png");
    private final Texture eight = new Texture("8.png");
    private final Texture nine = new Texture("9.png");
    private final Texture zero = new Texture("0.png");
    private final Texture scoreText = new Texture("ScoreText.png");

    void drawScore(Integer score, SpriteBatch batch) {
        List<Texture> numbers = transformNumberIntoImages(score);

        int startX = InGameMap.mapSizeX + SoloBlock.size;
        final int startY = ScoreMap.sizeY - 13 * SoloBlock.size;

        drawScoreText(batch);

        for (Texture number : numbers) {
            batch.draw(number, startX, startY);
            startX += SoloBlock.size;
        }
    }

    private void drawScoreText(SpriteBatch batch){
        batch.draw(scoreText, InGameMap.mapSizeX + SoloBlock.size, ScoreMap.sizeY - 10 * SoloBlock.size, 200, 200);
    }

    private Texture getNumberImage(char number) {
        System.out.println(number);
        switch (number) {
            case '1':
                return one;
            case '2':
                return two;
            case '3':
                return three;
            case '4':
                return four;
            case '5':
                return five;
            case '6':
                return six;
            case '7':
                return seven;
            case '8':
                return eight;
            case '9':
                return nine;
            case '0':
                return zero;
            default:
                throw new RuntimeException("Ошибка в получении изображения цифры");
        }
    }

    private List<Texture> transformNumberIntoImages(Integer score) {
        List<Texture> numbersImages = new ArrayList<>();

        String stringNumber = score.toString();
        char[] stringNumbers = stringNumber.toCharArray();

        for (char charNumber : stringNumbers) {
            numbersImages.add(getNumberImage(charNumber));
        }

        return numbersImages;
    }
}
