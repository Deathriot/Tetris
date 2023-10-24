package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.control.DeadManager;
import com.mygdx.game.control.Person;
import com.mygdx.game.generator.ShapeGenerator;

public abstract class Shape {
    public final Texture texture;
    public static boolean stop = false;
    protected static int nextId = 0;
    protected SoloBlock[] blocks = new SoloBlock[4];
    private final static int VELOCITY = SoloBlock.size / 10;
    private final static ShapeGenerator GENERATOR = new ShapeGenerator();
    protected int id;
    protected final int startX;

    public Shape(Texture texture, int startX) {
        this.startX = startX;
        this.texture = texture;
    }

    public static Shape GenerateShape() {
        nextId++;
        Shape newShape = GENERATOR.generateShape();

        if (InGameMap.alert) {
            for (SoloBlock block : newShape.blocks) {
                if (!InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size]) {
                    DeadManager.lose = true;
                    return newShape;
                }
            }
        }

        return newShape;
    }

    public void update(SpriteBatch batch) {
        moveShapeY(VELOCITY);

        for (SoloBlock block : blocks) {
            batch.draw(block.texture, block.x, block.y, SoloBlock.size, SoloBlock.size);
        }
    }

    public void moveShapeX(int amount) {
        for (SoloBlock block : blocks) {
            if (checkWillBeBlocked(block, amount)) {
                return;
            }
        }

        for (SoloBlock block : blocks) {
            block.x += amount;
        }
    }

    public void moveShapeY(int amount) {
        checkStop();

        if (stop) {
            stop = false;
            Person.stop = true;
            InGameMap.addStoppedShape(getMySelf());
            return;
        }

        for (SoloBlock block : blocks) {
            block.y -= amount;
        }
    }

    public static void fallBlock(SoloBlock block) {
        while (true) {
            if (block.y == 0) {
                return;
            }

            if(!InGameMap.isBlockEmpty[block.x / SoloBlock.size][(block.y) / SoloBlock.size - 1]){
                return;
            }

            block.y -= SoloBlock.size;
        }
    }

    protected boolean checkWillBeBlocked(SoloBlock block, int amount) {
        if ((block.x == InGameMap.mapSizeX - amount && amount > 0) || (block.x == 0 && amount < 0)) {
            return true;
        }

        for (Shape shape : InGameMap.getShapes()) {
            for (SoloBlock shapeBlock : shape.getBlocks()) {
                if (amount > 0) {
                    if (shapeBlock.x < block.x) {
                        continue;
                    }
                } else {
                    if (shapeBlock.x > block.x) {
                        continue;
                    }
                }

                if ((shapeBlock.x == block.x - amount || shapeBlock.x == block.x + amount)
                        && Math.abs(shapeBlock.y - block.y) < SoloBlock.size) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean checkRotateIsCorrect() {
        for (SoloBlock block : blocks) {
            if (block.y < 0 || block.x > InGameMap.mapSizeX - SoloBlock.size || block.x < 0) {
                return false;
            }

            if(!InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size]){
                return false;
            }
        }

        return true;
    }

    protected void checkStop() {
        for (SoloBlock block : blocks) {

            if(block.y % SoloBlock.size != 0){
                stop = false;
                return;
            }

            if (block.y == 0) {
                stop = true;
                return;
            }

            if(!InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size - 1]){
                stop = true;
                return;
            }
        }
    }

    protected abstract Shape getMySelf();
    public abstract void rotate();

    public SoloBlock[] getBlocks() {
        return blocks;
    }

    public int getStartX() {
        return startX;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Shape shape = (Shape) obj;
        return this.id == shape.id;
    }
}

