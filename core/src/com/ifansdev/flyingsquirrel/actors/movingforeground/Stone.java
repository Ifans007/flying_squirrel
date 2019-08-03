package com.ifansdev.flyingsquirrel.actors.movingforeground;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.assets.Assets;

public class Stone extends MovingForeground {

    private Rectangle boundStone;

    public Stone(MyGame myGame, int stoneNumber, int COUNT_STONE, Array<Stone> stoneArray, int screenWidth, float SPEED) {
        super(myGame.getAssets().getTexture(Assets.STONE), stoneNumber ,COUNT_STONE, stoneArray, screenWidth, SPEED);

        setRangeOffsetX(70);
        setX(getOffsetPositionX(stoneNumber * screenWidth / 2f));

        boundStone = new Rectangle(getX(), getY(), texture.getRegionWidth(), texture.getRegionHeight());

    }

    @Override
    void reposition(float x, float y) {
        super.reposition(x, y);
        boundStone.setPosition(getX(), getY());
    }

    public Rectangle getBoundStone() {
        return boundStone;
    }

}
