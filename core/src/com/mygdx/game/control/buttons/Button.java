package com.mygdx.game.control.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Button {
    private final static int WIDTH = 250;
    private final static int HEIGHT = 150;
    protected final int startX;
    protected final int startY;
    protected final Texture untouchedTexture;
    protected final Texture touchedTexture;
    private final ButtonAction action;

    public Button(int startX, int startY, Texture untouchedTexture, Texture touchedTexture, ButtonAction action) {
        this.startX = startX;
        this.startY = startY;
        this.untouchedTexture = untouchedTexture;
        this.touchedTexture = touchedTexture;
        this.action = action;
    }
    public ButtonAction update(SpriteBatch batch, Vector2 pos){
        if(pos.x > startX + WIDTH || pos.x < startX
        || pos.y > startY + HEIGHT || pos.y < startY){

            drawUntouched(batch);
            return ButtonAction.NOTHING;
        }

        drawTouched(batch);
        if(Gdx.input.isButtonJustPressed(0)){
            return action;
        }

        return ButtonAction.NOTHING;
    }

    private void drawUntouched(SpriteBatch batch){
        batch.draw(untouchedTexture, startX, startY, WIDTH, HEIGHT);
    }

    private void drawTouched(SpriteBatch batch){
        batch.draw(touchedTexture, startX, startY, WIDTH, HEIGHT);
    }

}
