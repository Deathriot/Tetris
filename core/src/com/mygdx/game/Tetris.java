package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Map.Screen;
import com.mygdx.game.control.DeadManager;
import com.mygdx.game.control.StartGameManager;

public class Tetris extends ApplicationAdapter {
    SpriteBatch batch;
    Screen screen;
    int count = 0;
    long sum = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        screen = new Screen(batch);
    }

    @Override
    public void render() {
        long timeStart = System.nanoTime();
        count ++;


        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();

        if(!screen.startGame()){
            return;
        }

        if(Screen.pause){
            screen.pause();
            return;
        }

        if(DeadManager.lose){
            screen.updateLost();
            return;
        }

        if(Screen.isAnimationGo){
            screen.animateBlockDestruction();
            return;
        }

        screen.update();
        batch.end();


        long timeEnd = System.nanoTime();
        sum += timeEnd - timeStart;

        if(count == 600){
            System.out.println(sum / 10);
            count = 0;
            sum = 0;
        }
    }
    @Override
    public void dispose() {
        batch.dispose();
    }
}
