package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.DamagedShape;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;

import java.util.*;

public final class InGameMap {
    public final static int mapSizeX = SoloBlock.size * 10;
    public final static int mapSizeY = SoloBlock.size * 20;
    public static boolean alert = false;
    public static boolean nextShape = false;
    private static final Set<Shape> shapes = new HashSet<>();
    private final static TreeMap<Integer, Integer> fullLines = new TreeMap<>();
    private static Drawer drawer;
    public final static boolean[][] isBlockEmpty = new boolean[10][20];

    public static void init(Drawer drawer1){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 10; j++){
                isBlockEmpty[j][i] = true;
            }
        }

        drawer = drawer1;
    }

    public static void update(SpriteBatch batch) {
        drawer.drawMap(shapes, batch);
        drawer.drawShapes(shapes, batch);
    }

    public static void drawLose(SpriteBatch batch) {
        drawer.drawMap(shapes, batch);
        drawer.drawLose(batch);
        drawer.drawButton(batch);
    }

    public static void addStoppedShape(Shape shape) {
        shapes.add(shape);
        addShapeBlocked(shape);
        checkRemoveLine(shape);
        //drawArray();

        if (fullLines.containsKey(850)) {
            alert = fullLines.get(850) > 0;
        }

        nextShape = true;
        //System.out.println(fullLines);
        //System.out.println(shapes);
    }

    private static void shapeFall(int y) {
        for (Integer coordinateY : fullLines.keySet()) {
            if (coordinateY < y) {
                continue;
            }

            for (Shape shape : shapes) {
                for (SoloBlock block : shape.getBlocks()) {
                    if (block.y < y) {
                        continue;
                    }

                    isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = true;
                    decreaseLineByBlock(block.y);
                    Shape.fallBlock(block);
                    increaseLineByBlock(block.y);
                    isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = false;
                }
            }
        }
    }

    private static void checkRemoveLine(Shape newShape) {
        for (SoloBlock block : newShape.getBlocks()) {
            increaseLineByBlock(block.y);
        }

        while (fullLines.containsValue(10)) {
            Integer y = 0;

            for (Integer coordinateY : fullLines.keySet()) {
                if (fullLines.get(coordinateY) == 10) {
                    y = coordinateY;
                }
            }

            final HashSet<Shape> destroyedShapes = new HashSet<>();
            final HashSet<DamagedShape> damagedShapes = new HashSet<>();

            for(int i = 0; i < 10; i++){
                isBlockEmpty[i][y / SoloBlock.size] = true;
            }

            loop:
            for (Shape shape : shapes) {
                for (SoloBlock block : shape.getBlocks()) {
                    if (block.y == y) {
                        destroyedShapes.add(shape);
                        DamagedShape damagedShape = new DamagedShape(shape, y);

                        if (!damagedShape.shouldBeDead) {
                            damagedShapes.add(damagedShape);
                        }

                        continue loop;
                    }
                }
            }

            shapes.removeAll(destroyedShapes);
            shapes.addAll(damagedShapes);

            final HashSet<DamagedShape> doomedShapes = new HashSet<>();

            for (Shape shape : shapes) {
                if (shape instanceof DamagedShape) {
                    DamagedShape doomedShape = (DamagedShape) shape;
                    doomedShape.checkDeath();
                    if (doomedShape.shouldBeDead) {
                        doomedShapes.add(doomedShape);
                    }
                }
            }

            shapes.removeAll(doomedShapes);

            fullLines.put(y, 0);
            ScoreMap.increaseScore();
            shapeFall(y);
        }
    }

    private static void increaseLineByBlock(Integer key) {
        if (!fullLines.containsKey(key)) {
            fullLines.put(key, 1);
        } else {
            fullLines.put(key, fullLines.get(key) + 1);
        }
    }

    private static void decreaseLineByBlock(Integer key) {
        fullLines.put(key, fullLines.get(key) - 1);
    }

    public static Set<Shape> getShapes() {
        return shapes;
    }

    public static void reset() {
        shapes.clear();
        fullLines.clear();

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 10; j++){
                isBlockEmpty[j][i] = true;
            }
        }
        alert = false;
        nextShape = true;
    }

    private static void addShapeBlocked(Shape shape){
        for(SoloBlock block: shape.getBlocks()){
            isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = false;
        }
    }

    private InGameMap() {

    }
}
