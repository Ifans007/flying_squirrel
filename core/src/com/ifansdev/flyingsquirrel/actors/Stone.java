package com.ifansdev.flyingsquirrel.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.assets.Assets;

import java.util.Random;

public class Stone extends Actor {
    private TextureRegion stone;

    private Array<Stone> stoneArray;

    private float screenWidth;

    private Random random;
    private final int RANGE_OFFSET = 70;
    private float randomOffsetX;

    private final float SPEED = 200f;

    private boolean lastStone;

    public Stone(MyGame myGame, int stoneNumber, int COUNT_STONE, Array<Stone> stoneArray, int screenWidth) {
        this.stoneArray = stoneArray;
        this.screenWidth = screenWidth;

        stone = myGame.getAssets().getTexture(Assets.STONE);

        random = new Random();
        randomOffsetX = random.nextInt(RANGE_OFFSET) - random.nextInt(RANGE_OFFSET);

        setX(getOffsetX(stoneNumber * screenWidth / 2f));

        if (stoneNumber == COUNT_STONE) {
            setLastStone(true);
        }

        System.out.println(this.screenWidth);

    }

    private float getOffsetX(float positionXLastStone) {
        return positionXLastStone + screenWidth / 2f + randomOffsetX;
    }

    @Override
    public void act(float delta) {
        setX(getX() - SPEED * delta);

        if (0 < getX() + stone.getRegionWidth()) {
            reposition(getX(), screenWidth - getX());

        } else {

                for (Stone stone : stoneArray) {
                    if (stone.isLastStone()) {

                        float positionXLastStone = stone.getX();
                        reposition(getOffsetX(positionXLastStone), 0);
                        setLastStone(true);
                        stone.setLastStone(false);
                        break;

                    }

            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(stone, getX(), getY());
    }

    private void reposition(float x, float y) {
        setX(x);
        setY((y / 4) - 10);
    }

    private void setLastStone(boolean lastStone) {
        this.lastStone = lastStone;
    }

    private boolean isLastStone() {
        return lastStone;
    }

}
