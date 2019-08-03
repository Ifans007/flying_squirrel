package com.ifansdev.flyingsquirrel.actors.movingforeground;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class MovingForeground<T extends MovingForeground> extends Actor {
    TextureRegion texture;
    int objectNumber;
    Array<T> objectArray;
    float screenWidth;
    float SPEED;

    Random random;
    int rangeOffsetX;
    int rangeOffsetY;

    int offsetX;
    int offsetY;

    boolean lastObject;

    MovingForeground(TextureRegion texture, int objectNumber, int COUNT_OBJECT, Array<T> objectArray, int screenWidth, float SPEED) {
        this.texture      = texture;
        this.objectNumber = objectNumber;
        this.objectArray  = objectArray;
        this.screenWidth  = screenWidth;
        this.SPEED        = SPEED;

        random = new Random();

        if (this.objectNumber == COUNT_OBJECT) {
            setLast(true);
        }
    }

    void setRangeOffsetX(int rangeOffsetX) {
        this.rangeOffsetX = rangeOffsetX;
    }

    void setRangeOffsetY(int rangeOffsetY) {
        this.rangeOffsetY = rangeOffsetY;
    }

    float getOffsetPositionX(float startPositionX) {
        try {
            return startPositionX + screenWidth / 2 + random.nextInt(rangeOffsetX) - random.nextInt(rangeOffsetX);
        } catch (IllegalArgumentException e) {
            System.err.println(this + ": rangeOffsetX must be positive");
        }

        return 0;
    }

    float getOffsetPositionY(float startPositionY) {
        try {
            return startPositionY + random.nextInt(rangeOffsetY);
        } catch (IllegalArgumentException e) {
            System.err.println(this + ": rangeOffsetY must be positive");
        }

        return 0;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    @Override
    public void act(float delta) {
        setX(getX() - SPEED * delta);

        if (0 < getX() + texture.getRegionWidth()) {
            reposition(getX(), screenWidth - getX());

        } else {

            for (T object : objectArray) {
                if (object.isLast()) {

                    float positionXLast = object.getX();
                    transfer(positionXLast, 0);
                    setLast(true);
                    object.setLast(false);
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY() - offsetY);
    }

    void reposition(float x, float y) {
        setX(x);
        setY((y / 4) - 10);
    }

    void transfer(float x, float y) {
        offsetX = (int) getOffsetPositionX(x);
        offsetY = (int) getOffsetPositionY(y);

        setX(offsetX);
        setY((y / 4) - 10);
    }

    void setLast(boolean lastObject) {
        this.lastObject = lastObject;
    }

    boolean isLast() {
        return lastObject;
    }
}
