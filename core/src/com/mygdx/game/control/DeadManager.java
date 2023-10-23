package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.Map.ScoreMap;

public final class DeadManager {
    public static boolean lose = false;

    public static void waitForEnter(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            lose = false;
            InGameMap.reset();
            ScoreMap.reset();;
        }
    }

    private DeadManager(){

    }
}
