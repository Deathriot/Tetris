package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Map.Drawer;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.Map.ScoreMap;
import com.mygdx.game.control.DeadManager;
import com.mygdx.game.control.Person;
import com.mygdx.game.model.*;

public class Tetris extends ApplicationAdapter {
    SpriteBatch batch;
    Shape nextShape;
    Person person;
    Drawer drawer;
    int count = 0;
    long sum = 0;

    @Override
    public void create() {
        drawer = new Drawer();
        InGameMap.init(drawer);
        ScoreMap.init(drawer);
        batch = new SpriteBatch();
        person = new Person(Shape.GenerateShape());
        nextShape = Shape.GenerateShape();
    }

    @Override
    public void render() {
        long timeStart = System.nanoTime();
        count ++;

        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();

        if (DeadManager.lose) {
            InGameMap.drawLose(batch);
            ScoreMap.drawLose(batch);
            DeadManager.waitForEnter();
            batch.end();
            return;
        }

        ScoreMap.update(batch, nextShape);
        person.update(batch);
        InGameMap.update(batch);


        if (InGameMap.nextShape) {
            person.changeShape(nextShape);
            nextShape = Shape.GenerateShape();
            InGameMap.nextShape = false;
        }

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
