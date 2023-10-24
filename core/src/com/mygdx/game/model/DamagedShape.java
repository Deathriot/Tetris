package com.mygdx.game.model;

// Поврежденая фигура
public class DamagedShape extends Shape {

    public boolean shouldBeDead = false;

    public DamagedShape(Shape shape, int y) {
        super(shape.texture, shape.startX);

        int deadCount = 0;
        id = shape.id;

        for (int i = 0; i < blocks.length; i++) {
            if (shape.getBlocks()[i].y == y) {
                blocks[i] = new SoloBlock(texture, -50, -50); // Удаленные блоки прячем за карту
                deadCount++;
                continue;
            }

            blocks[i] = shape.getBlocks()[i];
        }

        if (deadCount == blocks.length) {
            shouldBeDead = true;
        }
    }

    // Если все 4 блока уничтожены - фигуру необходимо удалять
    public void checkDeath() {
        int deadCount = 0;

        for (SoloBlock block : blocks) {
            if (block.x == -50) {
                deadCount++;
            }
        }

        if (deadCount == blocks.length) {
            shouldBeDead = true;
        }
    }

    @Override
    protected Shape getMySelf() {
        return this;
    }

    @Override
    public void rotate() {
        // nothing
    }
}
