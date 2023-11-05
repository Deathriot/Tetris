package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextInput {
    public static final int NUM_0 = 7;
    public static final int NUM_1 = 8;
    public static final int NUM_2 = 9;
    public static final int NUM_3 = 10;
    public static final int NUM_4 = 11;
    public static final int NUM_5 = 12;
    public static final int NUM_6 = 13;
    public static final int NUM_7 = 14;
    public static final int NUM_8 = 15;
    public static final int NUM_9 = 16;
    public static final int A = 29;
    public static final int B = 30;
    public static final int C = 31;
    public static final int D = 32;
    public static final int BACKSPACE = 67;
    public static final int E = 33;
    public static final int ENTER = 66;
    public static final int F = 34;
    public static final int G = 35;
    public static final int H = 36;
    public static final int I = 37;
    public static final int J = 38;
    public static final int K = 39;
    public static final int L = 40;
    public static final int M = 41;
    public static final int N = 42;
    public static final int O = 43;
    public static final int P = 44;
    public static final int Q = 45;
    public static final int R = 46;
    public static final int S = 47;
    public static final int SPACE = 62;
    public static final int T = 48;
    public static final int U = 49;
    public static final int V = 50;
    public static final int W = 51;
    public static final int X = 52;
    public static final int Y = 53;
    public static final int Z = 54;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final StringBuilder sb;
    public boolean isTyping = true;
    private final Texture line;

    public TextInput(SpriteBatch batch, BitmapFont font) {
        this.batch = batch;
        this.font = font;
        this.sb = new StringBuilder();

        Pixmap pixmap = new Pixmap(600, 5, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(0,0,800,5);
        line = new Texture(pixmap);
    }

    public void update() {
        batch.draw(line, 100, 210);
        font.draw(batch, "Please write your name", 170, 700);
        font.draw(batch, sb.toString(), 150, 250);

        String symb = getStringKey();
        if(!symb.isEmpty() && sb.length() < 25){
            sb.append(symb);
        }

        if(Gdx.input.isKeyJustPressed(BACKSPACE)){
            if(sb.length() > 0){
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        if(Gdx.input.isKeyJustPressed(ENTER)){
            isTyping = false;
        }
    }

    public String getName(){
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
