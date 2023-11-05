package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.control.buttons.*;
import com.mygdx.game.tools.TextInput;

import java.util.*;

public class StartGameManager {
    private static SpriteBatch batch;
    public static boolean isGameStarted = false;
    private static final Vector2 cursorPosition = new Vector2(0, 0);
    private static final Texture tetrisTheme = new Texture("startGame\\Tetris.png");
    private static final Button[] buttons = {new StartGameButton(), new InsertNameButton(), new HighScoreButton()};
    private static BitmapFont font32;
    private static ButtonAction action = ButtonAction.NOTHING;
    private static String highScore;
    private static TextInput input;
    private static String playerName = "";

    public static void init(BitmapFont font_32, SpriteBatch _batch) {
        loadScore();
        batch = _batch;
        font32 = font_32;

        input = new TextInput(batch, font32);
    }

    public static void update() {
        batch.draw(tetrisTheme, 150, 800);

        if (action.equals(ButtonAction.NOTHING)) {
            if(!playerName.isEmpty()){
                font32.draw(batch, "Player: " + playerName, 250, 700);
            }
            getPosition();
            for (Button button : buttons) {
                action = button.update(batch, cursorPosition);
                if (!action.equals(ButtonAction.NOTHING)) {
                    break;
                }
            }
        }

        switch (action) {
            case START_GAME:
                isGameStarted = true;
                action = ButtonAction.NOTHING;
                break;
            case INSERT_NAME:
                insertName();
                break;
            case VIEW_HIGH_SCORE:
                viewRecords();
                break;
        }
    }

    public static void insertName() {
        if(input.isTyping){
            input.update();
        }
        else{
            playerName = input.getName();
            input.isTyping = true;
            action = ButtonAction.NOTHING;
        }
    }

    public static void viewRecords() {
        if (highScore == null || highScore.isEmpty()) {
            font32.draw(batch, "No records here!", 200, 500);
        } else {
            font32.draw(batch, highScore, 200, 800);
        }

        font32.draw(batch, "Press esc to exit", 50, 50);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            action = ButtonAction.NOTHING;
        }
    }

    public static void loadScore(){
        String score = Gdx.files.local("startGame\\Scores.txt").readString();
        HashMap<String, Integer> scoreByName = parseScore(score);
        highScore = getSortedHighScore(scoreByName);
    }
    private static void getPosition() {
        cursorPosition.x = Gdx.input.getX();
        cursorPosition.y = InGameMap.mapSizeY - Gdx.input.getY();
    }

    private static HashMap<String, Integer> parseScore(String score) {
        if (score.isEmpty()) {
            return null;
        }

        HashMap<String, Integer> scores = new HashMap<>();

        String[] split = score.split("\r?\n");

        for (String line : split) {
            String[] value = line.split("-");
            String name = value[0];
            String stringNumber = value[1];
            int number = Integer.parseInt(stringNumber);

            if (scores.containsKey(name)) {
                if (number > scores.get(name)) {
                    scores.put(name, number);
                }
            } else {
                scores.put(name, number);
            }
        }

        return scores;
    }

    private static String getSortedHighScore(final HashMap<String, Integer> score) {
        if (score == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> numbers = new ArrayList<>(score.values());
        Collections.sort(numbers);
        List<Integer> sortedNumbers = new ArrayList<>();

        for (int i = numbers.size() - 1; i >= 0; i--) {
            sortedNumbers.add(numbers.get(i));
        }

        int i = 1;
        loop:
        for (Integer number : sortedNumbers) {
            if (i == 11) {
                break;
            }

            for (String name : score.keySet()) {
                if (score.get(name) == number) {
                    sb.append(i);
                    sb.append(". ");
                    sb.append(name);
                    sb.append(" - ");
                    sb.append(number);
                    sb.append("\n");
                    i++;
                    continue loop;
                }
            }
        }

        return sb.toString();
    }

    public static String getPlayerName() {
        return playerName;
    }
}
