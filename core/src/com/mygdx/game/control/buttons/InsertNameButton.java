package com.mygdx.game.control.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.control.StartGameManager;

public class InsertNameButton extends Button{
    public InsertNameButton() {
        super(500, 150, new Texture("startGame\\InsertNameButton.png")
                ,new Texture("startGame\\InsertNameButton_Touched.png"), ButtonAction.INSERT_NAME);
    }
}
