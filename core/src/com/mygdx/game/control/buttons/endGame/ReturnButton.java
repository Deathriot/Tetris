package com.mygdx.game.control.buttons.endGame;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.control.buttons.Button;
import com.mygdx.game.control.buttons.ButtonAction;

public class ReturnButton extends Button {
    public ReturnButton() {
        super(50, 0, new Texture("endGame\\ReturnButton.png")
                ,new Texture("endGame\\ReturnButton_Touched.png"), ButtonAction.RETURN);
    }
}
