package com.mygdx.game.Map;

import com.mygdx.game.model.DamagedShape;
import com.mygdx.game.model.Shape;
import com.mygdx.game.model.SoloBlock;
import com.mygdx.game.Map.drawer.Drawer;

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
    public static LinkedList<Integer> destructionLines;
    private static Shape lastShape;

    public static void init(Drawer drawer1) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                isBlockEmpty[j][i] = true;
            }
        }

        destructionLines = new LinkedList<>();
        drawer = drawer1;
    }

    public static void update() {

        if (fullLines.containsValue(10)) {
            for (Integer coordinateY : fullLines.keySet()) {
                if (fullLines.get(coordinateY) == 10) {
                    destructionLines.add(coordinateY);
                }
            }
        }

        if (!destructionLines.isEmpty()) {
            Screen.activateDestructionAnimation();
        }

        drawer.drawMap(shapes);
        drawer.drawShapes(shapes);
    }

    public static void drawShapes() {
        drawer.drawMap(shapes);
        drawer.drawShapes(shapes);
    }

    public static void drawLose() {
        drawer.drawMap(shapes);
        drawer.drawLose();
    }

    public static void addStoppedShape(Shape shape) {
        lastShape = shape;
        shapes.add(shape);
        addShapeBlocked(shape);

        for (SoloBlock block : shape.getBlocks()) {
            increaseLineByBlock(block.y);
        }

        if (fullLines.containsKey(800)) {
            alert = fullLines.get(800) > 0;
        }

        nextShape = true;
    }

    private static void shapeFall() {
        List<Integer> reversedLines = new ArrayList<>();

        for(int i = destructionLines.size() -1; i >= 0; i--){
            reversedLines.add(destructionLines.get(i));
        }

        for (Integer y : reversedLines) {
            for (Shape shape : shapes) {
                if (shape == lastShape) {
                    continue;
                }
                for (SoloBlock block : shape.getBlocks()) {
                    if (block.y < y) {
                        continue;
                    }
                    decreaseLineByBlock(block.y);
                    isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = true;
                }
            }

            for (Shape shape : shapes) {
                if (shape == lastShape) {
                    continue;
                }
                for (SoloBlock block : shape.getBlocks()) {
                    if(block.y < y){
                        continue;
                    }

                    block.y -= SoloBlock.size;
                    increaseLineByBlock(block.y);
                    isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = false;
                }
            }
        }

        lastShape.fall();
    }
    public static void removeLine() {
        for (Integer y : destructionLines) {
            final HashSet<Shape> destroyedShapes = new HashSet<>();
            final HashSet<DamagedShape> damagedShapes = new HashSet<>();

            for (int i = 0; i < 10; i++) {
                isBlockEmpty[i][y / SoloBlock.size] = true;
            }

            loop:
            for (Shape shape : shapes) {
                for (SoloBlock block : shape.getBlocks()) {
                    if (block.y == y) {
                        destroyedShapes.add(shape);
                        DamagedShape damagedShape = new DamagedShape(shape, y);

                        if (lastShape == shape) {
                            lastShape = damagedShape;
                        }

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

            ScoreMap.increaseScore();
            fullLines.put(y, 0);
        }

        shapeFall();
        destructionLines.clear();
    }

    public static void increaseLineByBlock(Integer key) {
        if (!fullLines.containsKey(key)) {
            fullLines.put(key, 1);
        } else {
            fullLines.put(key, fullLines.get(key) + 1);
        }
    }

    public static void decreaseLineByBlock(Integer key) {
        fullLines.put(key, fullLines.get(key) - 1);
    }

    public static Set<Shape> getShapes() {
        return shapes;
    }

    public static void reset() {
        shapes.clear();
        fullLines.clear();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                isBlockEmpty[j][i] = true;
            }
        }
        alert = false;
        nextShape = true;
    }

    private static void addShapeBlocked(Shape shape) {
        for (SoloBlock block : shape.getBlocks()) {
            isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = false;
        }
    }
    private InGameMap() {

    }
}
