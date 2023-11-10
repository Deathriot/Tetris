package com.mygdx.game.Map;

import com.mygdx.game.model.Shape;
import com.mygdx.game.Map.drawer.Drawer;
import com.mygdx.game.Map.drawer.ScoreDrawer;

public final class ScoreMap {
    public final static int sizeX = InGameMap.mapSizeX + 300;
    public final static int sizeY = InGameMap.mapSizeY;
    public static Integer score = 0;
    private static Drawer drawer;
    private static ScoreDrawer scoreDrawer;

    public static void init(Drawer drawer1, ScoreDrawer _scoreDrawer){
        scoreDrawer = _scoreDrawer;
        drawer = drawer1;
    }
    public static void update(Shape shape){
        drawer.drawLinesInCorner();
        drawer.drawNextShape(shape);
        scoreDrawer.drawScore(score);
    }

    public static void drawLose(){
        drawer.drawLinesInCorner();
        scoreDrawer.drawScore(score);
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
