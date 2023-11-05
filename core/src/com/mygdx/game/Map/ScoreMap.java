package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Shape;
import com.mygdx.game.Map.drawer.Drawer;
import com.mygdx.game.Map.drawer.ScoreDrawer;

public final class ScoreMap {
    public final static int sizeX = InGameMap.mapSizeX + 300;
    public final static int sizeY = InGameMap.mapSizeY;
    public static Integer score = 0;
    private static Drawer drawer;
    private final static ScoreDrawer scoreDrawer = new ScoreDrawer();

    public static void init(Drawer drawer1){
        drawer = drawer1;
    }
    public static void update(SpriteBatch batch, Shape shape){
        drawer.drawLinesInCorner(batch);
        drawer.drawNextShape(shape, batch);
        scoreDrawer.drawScore(score, batch);
    }

    public static void drawLose(SpriteBatch batch){
        drawer.drawLinesInCorner(batch);
        scoreDrawer.drawScore(score, batch);
    }
    static void increaseScore(){
        score ++;
    }

    public static void reset(){
        score = 0;
    }

    private ScoreMap(){

    }
}
