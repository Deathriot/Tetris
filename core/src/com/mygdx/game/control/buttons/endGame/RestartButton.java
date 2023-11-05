package com.mygdx.game.control.buttons.endGame;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.control.buttons.Button;
import com.mygdx.game.control.buttons.ButtonAction;

public class RestartButton extends Button {
    public RestartButton() {
        super(350, 0, new Texture("endGame\\RestartButton.png")
                ,new Texture("endGame\\RestartButton_Touched.png"), ButtonAction.RESTART);
    }
}
