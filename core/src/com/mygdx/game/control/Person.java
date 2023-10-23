package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

public final class Person {
    private final static int VELOCITYX = SoloBlock.size;
    private Shape shape;

    public Person(Shape shape) {
        this.shape = shape;
    }
    public void update(SpriteBatch batch){
        int key = getKey();

        switch (key){
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
                shape.rotate();
                break;
        }

        if(Shape.stop){
            Shape.stop = false;
            return;
        }

        shape.update(batch);
    }


    public void changeShape(Shape newShape){
        shape = newShape;
    }

    private int getKey() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            return Input.Keys.D;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            return Input.Keys.S;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            return Input.Keys.A;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            return Input.Keys.R;
        } else {
            return 0;
        }
    }
}
