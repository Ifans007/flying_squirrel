package com.ifansdev.flyingsquirrel.actors.movingforeground;

import com.badlogic.gdx.utils.Array;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.assets.Assets;

public class Flower extends MovingForeground {

    public Flower(MyGame myGame, int flowerNumber, int COUNT_FLOWER, Array<Flower> flowerArray, int screenWidth, float SPEED) {
        super(myGame.getAssets().getTexture(Assets.FLOWER), flowerNumber ,COUNT_FLOWER, flowerArray, screenWidth, SPEED);

        setRangeOffsetX(50);
        setRangeOffsetY(50);
        setOffsetY((int) getOffsetPositionY(0));
        setX(getOffsetPositionX(flowerNumber * screenWidth / 2f));
    }
}
