package com.ifansdev.flyingsquirrel.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ifansdev.flyingsquirrel.MyGame;
import com.ifansdev.flyingsquirrel.assets.Assets;

public class Squirrel extends Actor {
    private TextureRegion squirrelSpin;
    private TextureRegion squirrelDown;
    private Animation<TextureRegion> squirrelFlyAnimation;

    private float animationTime;
    private float rotateSquirrelSpin;

    private int yPosition;
    private int gravity;
    private int velocity;

    private boolean twoTouch;

    public Squirrel(MyGame myGame) {
        squirrelSpin = myGame.getAssets().getTexture(Assets.SQUIRREL_SPIN);
        squirrelDown = myGame.getAssets().getTexture(Assets.SQUIRREL_DOWN);

        TextureRegion[] squirrelFly = myGame.getAssets().getSquirrelFly();
        squirrelFlyAnimation = new Animation<>(0.1f, squirrelFly);
        squirrelFlyAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        setX(35);
        setY(68);
        yPosition = (int) getY();
        gravity = -13;
    }

    @Override
    public void act(float delta) {
        if (getY() > yPosition) velocity += gravity;
        setY(getY() + velocity * delta);

        if (getY() < yPosition) {
            gravity = -13;
            setY(yPosition);
        }

        rotateSquirrelSpin -= 1000 * delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (gravity == -13) {
            batch.draw(squirrelSpin, getX(), getY(),
                    squirrelSpin.getRegionWidth() / 2f, squirrelSpin.getRegionHeight() / 2f,
                    squirrelSpin.getRegionWidth(), squirrelSpin.getRegionHeight(), 1,1, rotateSquirrelSpin);
        }

        if (gravity == -1) {
            TextureRegion currentFrame = squirrelFlyAnimation.getKeyFrame(animationTime, true);
            batch.draw(currentFrame, getX() - 10, getY());
            animationTime += Gdx.graphics.getDeltaTime();

        }

        if (gravity == -110) {
            batch.draw(squirrelDown, getX(), getY());
        }
    }

    public void onClick() {
        if (getY() <= yPosition) {
            gravity = -13;
            velocity = 350;
            twoTouch = false;
        }

        if (twoTouch) gravity = -110;

        if (getY() > yPosition && !twoTouch) {
            gravity = -1;
            velocity = 40;
            twoTouch = true;
        }
    }

}
