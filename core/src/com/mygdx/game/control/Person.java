package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.Screen;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public final class Person {
    private final static int VELOCITYX = SoloBlock.size;
    private Shape shape;
    public static boolean stop = false;
    private int frameRight = 0;
    private int frameLeft = 0;
    private final static int framesDelay = 8;

    public Person(Shape shape) {
        this.shape = shape;
    }

    public void update(SpriteBatch batch) {
        int key = getKey();
        switch (key) {
            case Input.Keys.D:
                shape.moveShapeX(VELOCITYX);
                break;
            case Input.Keys.A:
                shape.moveShapeX(-VELOCITYX);
                break;
            case Input.Keys.S:
                shape.update(batch);
                break;
            case Input.Keys.R:
                shape = shape.rotate();
                break;
            case Input.Keys.SPACE:
                Screen.pause = true;
                stop = true;
                break;
        }

        if (stop) {
            stop = false;
            return;
        }

        shape.update(batch);
    }

    public void changeShape(Shape newShape) {
        shape = newShape;
    }

    public Shape getCurrentShape() {
        return shape;
    }

    private int getKey() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            frameRight++;
            if (frameRight >= framesDelay) {
                return Input.Keys.D;
            }
        } else{
            frameRight = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            frameLeft++;
            if (frameLeft >= framesDelay) {
                return Input.Keys.A;
            }
        } else{
            frameLeft = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D))
            return Input.Keys.D;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            return Input.Keys.S;
        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            return Input.Keys.A;
        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
            return Input.Keys.R;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            return Input.Keys.SPACE;

        return 0;
    }
}
