package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.Map.ScoreMap;
import com.mygdx.game.control.buttons.Button;
import com.mygdx.game.control.buttons.ButtonAction;
import com.mygdx.game.control.buttons.endGame.*;

public final class DeadManager {
    public static boolean lose = false;
    private static SpriteBatch batch;
    private final static FileHandle handle = Gdx.files.local("startGame\\Scores.txt");
    private static final Vector2 cursorPosition = new Vector2(0, 0);
    private static ButtonAction action = ButtonAction.NOTHING;
    private final static Button[] buttons = {new RestartButton(), new ReturnButton()};
    public static void init(SpriteBatch _batch){
        batch = _batch;
    }

    public static void update(){
        getPosition();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            restartGame();
            return;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            returnMenu();
            return;
        }

        if (action.equals(ButtonAction.NOTHING)) {
            getPosition();
            for (Button button : buttons) {
                action = button.update(batch, cursorPosition);
                if (!action.equals(ButtonAction.NOTHING)) {
                    break;
                }
            }
        }

        switch (action){
            case RETURN:
                returnMenu();
                return;
            case RESTART:
                restartGame();
        }
    }
    private static void restartGame(){
        lose = false;
        saveScore();
        InGameMap.reset();
        ScoreMap.reset();
        action = ButtonAction.NOTHING;
        StartGameManager.load();
    }

    private static void returnMenu(){
        StartGameManager.isGameStarted = false;
        lose = false;
        saveScore();
        InGameMap.reset();
        ScoreMap.reset();
        action = ButtonAction.NOTHING;
        StartGameManager.load();
    }
    private static void saveScore(){
        String playerName = StartGameManager.getPlayerName();
        if(playerName == null || playerName.isEmpty()){
            playerName = "Anonymus";
        }

        handle.writeString(playerName + "-" +ScoreMap.score + "\n", true);
    }

    private static void getPosition() {
        cursorPosition.x = Gdx.input.getX();
        cursorPosition.y = InGameMap.mapSizeY - Gdx.input.getY();
    }
    private DeadManager(){

    }
}
