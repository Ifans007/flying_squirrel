package com.ifansdev.flyingsquirrel.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.assets.Assets;

public class Forest extends Actor {

    private TextureRegion forest;
    private TextureRegion bg;

    public Forest(MyGame myGame) {

        forest = myGame.getAssets().getTexture(Assets.FOREST);
        bg     = myGame.getAssets().getTexture(Assets.BG);
    }

    public void act(float delta) {
        setX(getX() - 5 * delta);
        if (getX() <= -forest.getRegionWidth()) setX(0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bg, 0, 90);
        batch.draw(forest, getX(), getY());
        batch.draw(forest, getX() + forest.getRegionWidth(), getY());
    }
}
