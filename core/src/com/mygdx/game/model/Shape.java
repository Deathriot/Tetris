package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map.InGameMap;
import com.mygdx.game.control.DeadManager;
import com.mygdx.game.control.Person;
import com.mygdx.game.generator.ShapeGenerator;
import com.mygdx.game.model.shapes.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Shape {
    public final Texture texture;
    public static boolean stop = false;
    protected static int nextId = 0;
    protected List<SoloBlock> blocks = new ArrayList<>(4);
    private final static int VELOCITY = SoloBlock.size / 10;
    private final static ShapeGenerator GENERATOR = new ShapeGenerator();
    protected int id;
    protected final int startX;
    public SoloBlock axisRotationBlock;

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
            InGameMap.addStoppedShape(this);
            return;
        }

        for (SoloBlock block : blocks) {
            block.y -= amount;
        }
    }

    public void fall() {
        // Лямбды не работают!!!!!! Поэтому вот такой ужас
        List<Integer> sortedNumbers = new ArrayList<>();

        for (SoloBlock block : blocks) {
            sortedNumbers.add(block.y);
        }

        Collections.sort(sortedNumbers);

        LinkedList<SoloBlock> buffList = new LinkedList<>();

        for (Integer y : sortedNumbers) {
            for (SoloBlock block : blocks) {
                if (block.y == y) {
                    if(buffList.contains(block)){
                        continue;
                    }

                    buffList.add(block);
                    break;
                }
            }
        }

        blocks.clear();
        blocks.addAll(buffList);

        for (SoloBlock block : blocks) {
            InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = true;
            InGameMap.decreaseLineByBlock(block.y);

            while (true) {
                if (block.y == 0 || !InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size - 1]) {
                    InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size] = false;
                    InGameMap.increaseLineByBlock(block.y);
                    System.out.println(block.y);
                    break;
                }

                block.y -= SoloBlock.size;
            }
        }
        System.out.println();
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

    protected void checkStop() {
        stop = false;
        for (SoloBlock block : blocks) {

            if (block.y % SoloBlock.size != 0) {
                return;
            }

            if (block.y == 0 || !InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size - 1]) {
                stop = true;
                return;
            }
        }
    }

    public Shape rotate() {
        if (this instanceof Square) {
            return this;
        }

        Shape oldShape = getCopy();
        int outOfBoundsValue = 0;

        for (SoloBlock block : blocks) {
            int relativeX = block.x - axisRotationBlock.x;
            int relativeY = block.y - axisRotationBlock.y;

            int newX = (int) Math.round(relativeX * Math.cos(Math.toRadians(-90)) - relativeY * Math.sin(Math.toRadians(-90)));
            int newY = (int) Math.round(relativeX * Math.sin(Math.toRadians(-90)) + relativeY * Math.cos(Math.toRadians(-90)));

            block.x = axisRotationBlock.x + newX;
            block.y = axisRotationBlock.y + newY;

            if (block.x < 0) {
                outOfBoundsValue = Math.min(outOfBoundsValue, block.x);
            }

            if (block.x > InGameMap.mapSizeX - SoloBlock.size) {
                outOfBoundsValue = Math.max(outOfBoundsValue, block.x - InGameMap.mapSizeX + SoloBlock.size);
            }
        }

        shapeShift(outOfBoundsValue);

        for (SoloBlock block : blocks) {
            if (block.y < 0
                    || block.y >= InGameMap.mapSizeY - SoloBlock.size
                    || !InGameMap.isBlockEmpty[block.x / SoloBlock.size][block.y / SoloBlock.size]) {
                return oldShape;
            }
        }

        return this;
    }

    private void shapeShift(int shift) {
        for (SoloBlock block : blocks) {
            block.x -= shift;
        }
    }

    public void removeBlocks(int y) {
        List<SoloBlock> removedBlocks = new ArrayList<>();
        for (SoloBlock blocK : blocks) {
            if (blocK.y == y) {
                removedBlocks.add(blocK);
            }
        }

        blocks.removeAll(removedBlocks);
    }
    protected abstract Shape getCopy();

    public List<SoloBlock> getBlocks() {
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

