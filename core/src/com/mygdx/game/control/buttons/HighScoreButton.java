package com.mygdx.game.control.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.control.StartGameManager;

public class HighScoreButton extends Button{
    public HighScoreButton() {
        super(275, 375, new Texture("startGame\\RecordsButton.png")
                ,new Texture("startGame\\RecordsButton_Touched.png"), ButtonAction.VIEW_HIGH_SCORE);
    }
}
