package com.mygdx.game.Map.drawer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.Map.ScoreMap;
import com.mygdx.game.model.SoloBlock;

import java.util.ArrayList;
import java.util.List;

public class ScoreDrawer {
    private final Texture one = new Texture("numbers\\1.png");
    private final Texture two = new Texture("numbers\\2.png");
    private final Texture three = new Texture("numbers\\3.png");
    private final Texture four = new Texture("numbers\\4.png");
    private final Texture five = new Texture("numbers\\5.png");
    private final Texture six = new Texture("numbers\\6.png");
    private final Texture seven = new Texture("numbers\\7.png");
    private final Texture eight = new Texture("numbers\\8.png");
    private final Texture nine = new Texture("numbers\\9.png");
    private final Texture zero = new Texture("numbers\\0.png");
    private final BitmapFont scoreText;
    private final SpriteBatch batch;

    public ScoreDrawer(SpriteBatch batch, BitmapFont font72) {
        this.batch = batch;
        this.scoreText = font72;
    }

    public void drawScore(Integer score) {
        List<Texture> numbers = transformNumberIntoImages(score);

        int startX = InGameMap.mapSizeX + SoloBlock.size;
        final int startY = ScoreMap.sizeY - 14 * SoloBlock.size;

        drawScoreText();

        for (Texture number : numbers) {
            batch.draw(number, startX, startY);
            startX += SoloBlock.size;
        }
    }

    private void drawScoreText(){
        scoreText.draw(batch, "Score:",InGameMap.mapSizeX + SoloBlock.size
                ,ScoreMap.sizeY - 10 * SoloBlock.size);
    }

    private Texture getNumberImage(char number) {
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
