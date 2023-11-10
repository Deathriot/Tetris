package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextInput {
    private static final int NUM_0 = 7;
    private static final int NUM_1 = 8;
    private static final int NUM_2 = 9;
    private static final int NUM_3 = 10;
    private static final int NUM_4 = 11;
    private static final int NUM_5 = 12;
    private static final int NUM_6 = 13;
    private static final int NUM_7 = 14;
    private static final int NUM_8 = 15;
    private static final int NUM_9 = 16;
    private static final int A = 29;
    private static final int B = 30;
    private static final int C = 31;
    private static final int D = 32;
    private static final int BACKSPACE = 67;
    private static final int E = 33;
    private static final int ENTER = 66;
    private static final int F = 34;
    private static final int G = 35;
    private static final int H = 36;
    private static final int I = 37;
    private static final int J = 38;
    private static final int K = 39;
    private static final int L = 40;
    private static final int M = 41;
    private static final int N = 42;
    private static final int O = 43;
    private static final int P = 44;
    private static final int Q = 45;
    private static final int R = 46;
    private static final int S = 47;
    private static final int SPACE = 62;
    private static final int T = 48;
    private static final int U = 49;
    private static final int V = 50;
    private static final int W = 51;
    private static final int X = 52;
    private static final int Y = 53;
    private static final int Z = 54;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final StringBuilder sb;
    private final Texture line;
    private final Texture verticalLine;
    private int EraseFrame = 0;
    private int frame;
    private int pointerPos = 0;
    public boolean isTyping = true;

    public TextInput(SpriteBatch batch, BitmapFont font) {
        this.batch = batch;
        this.font = font;
        this.sb = new StringBuilder();

        Pixmap black = new Pixmap(600, 4, Pixmap.Format.RGB888);
        black.setColor(Color.BLACK);
        black.fillRectangle(0, 0, 800, 5);
        line = new Texture(black);

        Pixmap grey = new Pixmap(2, 30, Pixmap.Format.RGB888);
        grey.setColor(Color.BLACK);
        grey.fillRectangle(0, 0, 2, 30);
        verticalLine = new Texture(grey);
    }

    public void update() {
        batch.draw(line, 100, 210);
        font.draw(batch, "Please write your name", 170, 700);
        font.draw(batch, sb.toString(), 150, 250);
        drawPointer();

        String symb = getStringKey();
        if (!symb.isEmpty() && sb.length() < 20) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                sb.insert(pointerPos, symb);
            } else {
                sb.insert(pointerPos, symb.toLowerCase());
            }

            movePointerRight();
        }

        erase();

        if (Gdx.input.isKeyJustPressed(ENTER)) {
            isTyping = false;
        }
    }

    private void drawPointer() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            movePointerLeft();
            frame = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            movePointerRight();
            frame = 0;
        }

        frame++;
        if (frame % 60 < 30) {
            batch.draw(verticalLine, pointerPos * 21 + 170 - 21, 223);
        }
    }

    private void erase() {
        if (pointerPos > 0) {
            if (Gdx.input.isKeyJustPressed(BACKSPACE)) {
                sb.deleteCharAt(pointerPos - 1);
                movePointerLeft();
                EraseFrame = 0;
            }

            if (Gdx.input.isKeyPressed(BACKSPACE)) {
                EraseFrame++;

                if (EraseFrame > 5) {
                    sb.deleteCharAt(pointerPos - 1);
                    movePointerLeft();
                    EraseFrame = 0;
                }
            }
        }

        if (pointerPos >= sb.length()) {
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
            sb.deleteCharAt(pointerPos);
            EraseFrame = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.FORWARD_DEL)) {
            EraseFrame++;

            if (EraseFrame > 5) {
                sb.deleteCharAt(pointerPos);
                EraseFrame = 0;
            }
        }
    }

    private void movePointerRight() {
        if (pointerPos >= sb.length()) {
            return;
        }

        pointerPos++;
    }

    private void movePointerLeft() {
        if (pointerPos <= 0) {
            return;
        }

        pointerPos--;
    }

    public String getName() {
        return sb.toString();
    }

    private String getStringKey() {
        if (Gdx.input.isKeyJustPressed(NUM_0))
            return "0";
        if (Gdx.input.isKeyJustPressed(NUM_1))
            return "1";
        if (Gdx.input.isKeyJustPressed(NUM_2))
            return "2";
        if (Gdx.input.isKeyJustPressed(NUM_3))
            return "3";
        if (Gdx.input.isKeyJustPressed(NUM_4))
            return "4";
        if (Gdx.input.isKeyJustPressed(NUM_5))
            return "5";
        if (Gdx.input.isKeyJustPressed(NUM_6))
            return "6";
        if (Gdx.input.isKeyJustPressed(NUM_7))
            return "7";
        if (Gdx.input.isKeyJustPressed(NUM_8))
            return "8";
        if (Gdx.input.isKeyJustPressed(NUM_9))
            return "9";
        if (Gdx.input.isKeyJustPressed(A))
            return "A";
        if (Gdx.input.isKeyJustPressed(B))
            return "B";
        if (Gdx.input.isKeyJustPressed(C))
            return "C";
        if (Gdx.input.isKeyJustPressed(D))
            return "D";
        if (Gdx.input.isKeyJustPressed(E))
            return "E";
        if (Gdx.input.isKeyJustPressed(F))
            return "F";
        if (Gdx.input.isKeyJustPressed(G))
            return "G";
        if (Gdx.input.isKeyJustPressed(H))
            return "H";
        if (Gdx.input.isKeyJustPressed(I))
            return "I";
        if (Gdx.input.isKeyJustPressed(J))
            return "J";
        if (Gdx.input.isKeyJustPressed(K))
            return "K";
        if (Gdx.input.isKeyJustPressed(L))
            return "L";
        if (Gdx.input.isKeyJustPressed(M))
            return "M";
        if (Gdx.input.isKeyJustPressed(N))
            return "N";
        if (Gdx.input.isKeyJustPressed(O))
            return "O";
        if (Gdx.input.isKeyJustPressed(P))
            return "P";
        if (Gdx.input.isKeyJustPressed(Q))
            return "Q";
        if (Gdx.input.isKeyJustPressed(R))
            return "R";
        if (Gdx.input.isKeyJustPressed(S))
            return "S";
        if (Gdx.input.isKeyJustPressed(T))
            return "T";
        if (Gdx.input.isKeyJustPressed(U))
            return "U";
        if (Gdx.input.isKeyJustPressed(V))
            return "V";
        if (Gdx.input.isKeyJustPressed(W))
            return "W";
        if (Gdx.input.isKeyJustPressed(X))
            return "X";
        if (Gdx.input.isKeyJustPressed(Y))
            return "Y";
        if (Gdx.input.isKeyJustPressed(Z))
            return "Z";
        if (Gdx.input.isKeyJustPressed(SPACE))
            return " ";

        return "";
    }
}
