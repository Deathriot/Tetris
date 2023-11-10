package com.mygdx.game.control.buttons.startGame;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.control.buttons.Button;
import com.mygdx.game.control.buttons.ButtonAction;
public class StartGameButton extends Button{

    public StartGameButton() {
        super(50, 150, new Texture("startGame\\Button.png")
                ,new Texture("startGame\\Button_Touched.png"), ButtonAction.START_GAME);
    }
}
