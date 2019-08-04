package com.ifansdev.flyingsquirrel.actors.movingforeground;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.actors.Score;
import com.ifansdev.flyingsquirrel.assets.Assets;

public class Stone extends MovingForeground {
    private Score score;

    private Rectangle boundStone;

    public Stone(MyGame myGame, int stoneNumber, int COUNT_STONE, Array<Stone> stoneArray, int screenWidth, float SPEED, Score score) {
        super(myGame.getAssets().getTexture(Assets.STONE), stoneNumber ,COUNT_STONE, stoneArray, screenWidth, SPEED);
        this.score = score;

        setRangeOffsetX(70);
        setX(getOffsetPositionX(stoneNumber * screenWidth / 2f));

        boundStone = new Rectangle(getX(), getY(), texture.getRegionWidth(), texture.getRegionHeight());

    }

    @Override
    void reposition(float x, float y) {
        super.reposition(x, y);
        boundStone.setPosition(getX(), getY());
    }

    @Override
    void transfer(float x, float y) {
        super.transfer(x, y);
        score.scoreIncrement();
    }

    public Rectangle getBoundStone() {
        return boundStone;
    }

}
