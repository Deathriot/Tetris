package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.drawer.AnimationDrawer;
import com.mygdx.game.Map.drawer.Drawer;
import com.mygdx.game.control.DeadManager;
import com.mygdx.game.control.Person;
import com.mygdx.game.control.StartGameManager;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

import java.util.List;


public class Screen {
    public static boolean isAnimationGo = false;
    public static boolean pause = false;
    private static int startY;
    private static int endY;
    private final SpriteBatch batch;
    private Shape nextShape;
    private final Person person;
    private static final BitmapFont font72 = new BitmapFont(Gdx.files.internal("fonts\\font72.fnt"));
    private static List<Integer> destructedLines;


    public Screen(SpriteBatch batch) {
        Drawer drawer = new Drawer();
        BitmapFont font32 = new BitmapFont(Gdx.files.internal("fonts\\font32.fnt"));

        StartGameManager.init(font32, batch);
        DeadManager.init(batch);
        InGameMap.init(drawer);
        ScoreMap.init(drawer);
        AnimationDrawer.init();

        this.person = new Person(Shape.GenerateShape());
        this.nextShape = Shape.GenerateShape();
        this.batch = batch;
    }

    public void update(){
        InGameMap.update(batch);
        ScoreMap.update(batch, nextShape);
        person.update(batch);

        if (InGameMap.nextShape) {
            person.changeShape(nextShape);
            nextShape = Shape.GenerateShape();
            InGameMap.nextShape = false;
        }
    }

    public void updateLost(){
        InGameMap.drawLose(batch);
        ScoreMap.drawLose(batch);
        DeadManager.update();
        batch.end();
    }

    public boolean startGame(){
        if(StartGameManager.isGameStarted){
            return true;
        }

        StartGameManager.update();
        batch.end();
        return false;
    }

    public void pause(){
        InGameMap.drawShapes(batch);
        ScoreMap.update(batch, nextShape);

        for (SoloBlock block : person.getCurrentShape().getBlocks()) {
            batch.draw(block.texture, block.x, block.y, SoloBlock.size, SoloBlock.size);
        }

        font72.draw(batch, "Pause", 200, 500);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            pause = false;
        }

        batch.end();
    }
    public void animateBlockDestruction(){
        InGameMap.drawShapes(batch);
        ScoreMap.update(batch, nextShape);

        if(!AnimationDrawer.drawDestroyAnimation(destructedLines, batch, person.getCurrentShape())){
            isAnimationGo = false;
            InGameMap.removeLine();
        }

        batch.end();
    }

    static void activateDestructionAnimation(){
        destructedLines = InGameMap.destructionLines;
        isAnimationGo = true;
    }
}
